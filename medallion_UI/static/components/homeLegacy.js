import Chart from 'chart.js/auto';
import { getMedallionData } from './apiClient';

export function initHomePage() {

  // ── FIX: store the analysed stock name so redirectToStock() always has it ──
  let currentStock = '';

  let priceChartInst = null, simChartInst = null, volChartInst = null;
  let candleChartState = null;
  let currentCandleTimeframe = '6m';
  let candleResizeBound = false;

  const formEl = document.getElementById('search-form');
  const stockInputEl = document.getElementById('inp-stock');
  const backBtnEl = document.getElementById('back-btn');
  const onFormSubmit = event => {
    event.preventDefault();
    runSearch();
  };
  const onStockInputKeyDown = e => {
    if (e.key === 'Enter') runSearch();
  };

  formEl.addEventListener('submit', onFormSubmit);
  stockInputEl.addEventListener('keydown', onStockInputKeyDown);
  if (backBtnEl) backBtnEl.addEventListener('click', showSearch);
  initCandleToolbar();

  function updateStockLink() {
      const stock = currentStock || document.getElementById('inp-stock').value.trim();

      const link = document.getElementById('stockDetailsLink');

      if (stock) {
          const encodedStock = encodeURIComponent(stock);
          link.href = '/stockdetails?stockname=' + encodedStock;
      } else {
          link.href = "#";
      }
  }

  function formatPeriodLabel(period) {
    const labels = {
      '1m': '1 Month',
      '3m': '3 Months',
      '6m': '6 Months',
      '1y': '1 Year',
      '2y': '2 Years',
      '5y': '5 Years',
      'all': 'All history'
    };
    return labels[period] || period;
  }

  function getSimulationTitle(days) {
    return `${days}-day simulation outlook`;
  }

  function getSimulationPathsTitle(days) {
    return `Monte Carlo simulation paths · ${days} days`;
  }

  function getRangeTitle(days) {
    return `${days}-day final price corridor`;
  }

  function getInitialCandleTimeframe(period) {
    return ['1m', '3m', '6m', '1y'].includes(period) ? period : 'all';
  }

  function setSignalBadge(element, label, tone) {
    element.textContent = label;
    element.className = 'signal-badge ' + tone;
  }

  function initCandleToolbar() {
    document.querySelectorAll('#candle-toolbar .time-chip').forEach(btn => {
      btn.addEventListener('click', () => {
        currentCandleTimeframe = btn.dataset.tf;
        syncCandleToolbar(currentCandleTimeframe);
        if (candleChartState) {
          drawCandlestickChart(
            candleChartState.prices,
            candleChartState.volumes,
            candleChartState.dma50,
            candleChartState.dma200,
            currentCandleTimeframe
          );
        }
      });
    });
  }

  function syncCandleToolbar(timeframe) {
    document.querySelectorAll('#candle-toolbar .time-chip').forEach(btn => {
      btn.classList.toggle('active', btn.dataset.tf === timeframe);
    });
  }

  function getDeterministicNoise(seed) {
    const raw = Math.sin(seed * 12.9898) * 43758.5453;
    return raw - Math.floor(raw);
  }

  function getTimeframeCutoff(timeframe) {
    if (timeframe === 'all') return null;
    const match = /^([0-9]+)([my])$/.exec(timeframe);
    if (!match) return null;
    const amount = parseInt(match[1], 10);
    const unit = match[2];
    const date = new Date();
    if (unit === 'm') {
      date.setMonth(date.getMonth() - amount);
    } else {
      date.setFullYear(date.getFullYear() - amount);
    }
    return date;
  }

  function drawCandlestickChart(priceData, volumeData, dma50Data, dma200Data, timeframe) {
    const priceCanvas = document.getElementById('candleChart');
    const volumeCanvas = document.getElementById('candleVolumeChart');
    const tooltip = document.getElementById('candleTooltip');
    if (!priceCanvas || !volumeCanvas || !tooltip || !priceData || priceData.length === 0) return;

    const priceStage = priceCanvas.parentElement;
    const ratio = window.devicePixelRatio || 1;
    const priceWidth = Math.max(320, priceStage.clientWidth);
    const priceHeight = Math.max(220, priceStage.clientHeight);
    const volumeHeight = Math.max(60, volumeCanvas.parentElement.clientHeight);

    priceCanvas.width = priceWidth * ratio;
    priceCanvas.height = priceHeight * ratio;
    priceCanvas.style.width = priceWidth + 'px';
    priceCanvas.style.height = priceHeight + 'px';

    volumeCanvas.width = priceWidth * ratio;
    volumeCanvas.height = volumeHeight * ratio;
    volumeCanvas.style.width = priceWidth + 'px';
    volumeCanvas.style.height = volumeHeight + 'px';

    const pCtx = priceCanvas.getContext('2d');
    const vCtx = volumeCanvas.getContext('2d');
    pCtx.setTransform(ratio, 0, 0, ratio, 0, 0);
    vCtx.setTransform(ratio, 0, 0, ratio, 0, 0);

    let filteredPrice = priceData;
    let filteredVolume = volumeData || [];
    let filteredDma50 = dma50Data || [];
    let filteredDma200 = dma200Data || [];
    const cutoff = getTimeframeCutoff(timeframe);

    if (cutoff) {
      filteredPrice = priceData.filter(point => new Date(point.date) >= cutoff);
    }

    if (filteredPrice.length === 0) {
      filteredPrice = priceData.slice(-30);
    }

    if (filteredPrice.length > 0) {
      const startDate = filteredPrice[0].date;
      const endDate = filteredPrice[filteredPrice.length - 1].date;
      filteredVolume = filteredVolume.filter(point => point.date >= startDate && point.date <= endDate);
      filteredDma50 = filteredDma50.filter(point => point.date >= startDate && point.date <= endDate);
      filteredDma200 = filteredDma200.filter(point => point.date >= startDate && point.date <= endDate);
    }

    const candles = filteredPrice
      .map((point, index) => {
        const close = Number(point.value);
        const prevClose = index > 0 ? Number(filteredPrice[index - 1].value) : close;
        if (!Number.isFinite(close) || !Number.isFinite(prevClose)) return null;
        const open = prevClose;
        const amplitude = Math.max(Math.abs(close - open) * 0.42, close * 0.0055);
        const upperNoise = getDeterministicNoise(index + 1.17);
        const lowerNoise = getDeterministicNoise(index + 7.31);
        return {
          date: point.date,
          open,
          close,
          high: Math.max(open, close) + amplitude * (0.45 + upperNoise * 0.75),
          low: Math.min(open, close) - amplitude * (0.45 + lowerNoise * 0.75),
          volume: filteredVolume[index] ? Number(filteredVolume[index].volume ?? filteredVolume[index].value) : 0
        };
      })
      .filter(Boolean);

    if (candles.length === 0) return;

    const dma50Vals = filteredDma50.map(point => Number(point.value)).filter(Number.isFinite);
    const dma200Vals = filteredDma200.map(point => Number(point.value)).filter(Number.isFinite);
    const highs = candles.map(candle => candle.high);
    const lows = candles.map(candle => candle.low);
    const values = [...highs, ...lows, ...dma50Vals, ...dma200Vals].filter(Number.isFinite);
    const minPrice = Math.min(...values) * 0.985;
    const maxPrice = Math.max(...values) * 1.015;
    const maxVolume = Math.max(...candles.map(candle => candle.volume || 0), 1);

    const padding = { left: 62, right: 20, top: 20, bottom: 28 };
    const chartWidth = priceWidth - padding.left - padding.right;
    const chartHeight = priceHeight - padding.top - padding.bottom;

    pCtx.clearRect(0, 0, priceWidth, priceHeight);
    vCtx.clearRect(0, 0, priceWidth, volumeHeight);

    pCtx.strokeStyle = 'rgba(123, 100, 68, 0.12)';
    pCtx.lineWidth = 1;
    for (let i = 0; i <= 5; i++) {
      const y = padding.top + (chartHeight * i / 5);
      pCtx.beginPath();
      pCtx.moveTo(padding.left, y);
      pCtx.lineTo(priceWidth - padding.right, y);
      pCtx.stroke();

      const labelPrice = maxPrice - ((maxPrice - minPrice) * i / 5);
      pCtx.fillStyle = '#6e5a48';
      pCtx.font = '11px JetBrains Mono';
      pCtx.textAlign = 'right';
      pCtx.fillText('₹' + labelPrice.toFixed(2), padding.left - 8, y + 4);
    }

    const candleSpacing = chartWidth / candles.length;
    const candleWidth = Math.max(2, Math.min(10, candleSpacing * 0.62));

    candles.forEach((candle, index) => {
      const x = padding.left + (index + 0.5) * candleSpacing;
      const isUp = candle.close >= candle.open;
      const color = isUp ? '#15803d' : '#b91c1c';
      const fill = isUp ? 'rgba(21, 128, 61, 0.88)' : 'rgba(185, 28, 28, 0.88)';
      const yHigh = padding.top + ((maxPrice - candle.high) / (maxPrice - minPrice)) * chartHeight;
      const yLow = padding.top + ((maxPrice - candle.low) / (maxPrice - minPrice)) * chartHeight;
      const yOpen = padding.top + ((maxPrice - candle.open) / (maxPrice - minPrice)) * chartHeight;
      const yClose = padding.top + ((maxPrice - candle.close) / (maxPrice - minPrice)) * chartHeight;
      const bodyTop = Math.min(yOpen, yClose);
      const bodyHeight = Math.max(2, Math.abs(yClose - yOpen));

      pCtx.beginPath();
      pCtx.strokeStyle = color;
      pCtx.lineWidth = 1.25;
      pCtx.moveTo(x, yHigh);
      pCtx.lineTo(x, yLow);
      pCtx.stroke();

      pCtx.fillStyle = fill;
      pCtx.fillRect(x - candleWidth / 2, bodyTop, candleWidth, bodyHeight);
      pCtx.strokeStyle = color;
      pCtx.lineWidth = 1;
      pCtx.strokeRect(x - candleWidth / 2, bodyTop, candleWidth, bodyHeight);
    });

    function drawMovingAverage(valuesList, color) {
      if (!valuesList.length) return;
      pCtx.beginPath();
      pCtx.strokeStyle = color;
      pCtx.lineWidth = 1.6;
      pCtx.lineJoin = 'round';
      pCtx.lineCap = 'round';
      valuesList.forEach((value, index) => {
        const x = padding.left + (index + 0.5) * candleSpacing;
        const y = padding.top + ((maxPrice - value) / (maxPrice - minPrice)) * chartHeight;
        if (index === 0) pCtx.moveTo(x, y);
        else pCtx.lineTo(x, y);
      });
      pCtx.stroke();
    }

    drawMovingAverage(dma200Vals, '#f59e0b');
    drawMovingAverage(dma50Vals, '#8b5cf6');

    const labelCount = Math.min(6, candles.length);
    pCtx.fillStyle = '#6e5a48';
    pCtx.font = '10px Space Grotesk';
    pCtx.textAlign = 'center';
    for (let i = 0; i < labelCount; i++) {
      const index = labelCount === 1 ? 0 : Math.floor(i * (candles.length - 1) / (labelCount - 1));
      const x = padding.left + (index + 0.5) * candleSpacing;
      const date = new Date(candles[index].date);
      const label = Number.isNaN(date.getTime())
        ? candles[index].date
        : date.toLocaleDateString('en-IN', { day: '2-digit', month: 'short' });
      pCtx.fillText(label, x, priceHeight - 8);
    }

    const vPadding = { left: 62, right: 20, top: 6, bottom: 8 };
    const vChartHeight = volumeHeight - vPadding.top - vPadding.bottom;
    const volBarSpacing = (priceWidth - vPadding.left - vPadding.right) / candles.length;
    const volBarWidth = Math.max(1, volBarSpacing * 0.62);

    candles.forEach((candle, index) => {
      if (!Number.isFinite(candle.volume) || candle.volume <= 0) return;
      const x = vPadding.left + (index + 0.5) * volBarSpacing;
      const barHeight = (candle.volume / maxVolume) * vChartHeight;
      const isUp = candle.close >= candle.open;
      vCtx.fillStyle = isUp ? 'rgba(21, 128, 61, 0.46)' : 'rgba(185, 28, 28, 0.42)';
      vCtx.fillRect(x - volBarWidth / 2, volumeHeight - vPadding.bottom - barHeight, volBarWidth, barHeight);
    });

    priceCanvas.onmousemove = event => {
      const rect = priceCanvas.getBoundingClientRect();
      const x = event.clientX - rect.left;
      const index = Math.floor((x - padding.left) / candleSpacing);
      if (index < 0 || index >= candles.length) {
        tooltip.style.display = 'none';
        return;
      }

      const candle = candles[index];
      const isUp = candle.close >= candle.open;
      const candleX = padding.left + (index + 0.5) * candleSpacing;
      const candleY = padding.top + ((maxPrice - candle.close) / (maxPrice - minPrice)) * chartHeight;
      const date = new Date(candle.date);
      const formattedDate = Number.isNaN(date.getTime())
        ? candle.date
        : date.toLocaleDateString('en-IN', { day: '2-digit', month: 'short', year: 'numeric' });

      tooltip.innerHTML = `
        <div style="font-weight:700;color:${isUp ? '#7cf0a3' : '#ff9d9d'};margin-bottom:4px;">₹${candle.close.toFixed(2)}</div>
        <div>O: ₹${candle.open.toFixed(2)}  H: ₹${candle.high.toFixed(2)}</div>
        <div>L: ₹${candle.low.toFixed(2)}  C: ₹${candle.close.toFixed(2)}</div>
        <div style="opacity:0.76;margin-top:4px;">${formattedDate}</div>
      `;
      tooltip.style.display = 'block';
      tooltip.style.left = candleX + 'px';
      tooltip.style.top = Math.max(44, candleY) + 'px';
    };

    priceCanvas.onmouseleave = () => {
      tooltip.style.display = 'none';
    };

    if (!candleResizeBound) {
      window.addEventListener('resize', () => {
        if (candleChartState) {
          drawCandlestickChart(
            candleChartState.prices,
            candleChartState.volumes,
            candleChartState.dma50,
            candleChartState.dma200,
            currentCandleTimeframe
          );
        }
      });
      candleResizeBound = true;
    }
  }

  async function runSearch() {
    const stock = document.getElementById('inp-stock').value.trim();
    const period = document.getElementById('inp-period').value;
    const days = parseInt(document.getElementById('inp-days').value) || 30;
    const count = parseInt(document.getElementById('inp-simulation_count').value) || 10;

    if (!stock) { showError('Please enter a stock name.'); return; }
    setLoading(true);
    hideError();

    try {
      const data = await getMedallionData({ stock, period, days, count });
      renderDashboard(data, stock, period, days);
	  updateStockLink();
    } catch (err) {
      showError('Could not reach the API. Make sure localhost:8080 is running. (' + err.message + ')');
    } finally {
      setLoading(false);
    }
  }

  function renderDashboard(data, stock, period, days) {
    // ── FIX: save the stock name for use by redirectToStock() ──
    currentStock = stock;

    const prices    = data.dataSetDto?.price?.values       ?? [];
    const volumes   = data.dataSetDto?.volumeData?.values  ?? [];
    const dma50vals = data.dataSetDto?.dma50?.values       ?? [];
    const dma200vals= data.dataSetDto?.dma200?.values      ?? [];
    const oneday    = data.oneday  ?? {};
    const sims      = data.multidays ?? [];

    const lastPrice  = prices.length    ? prices[prices.length - 1].value       : 0;
    const lastDma50  = dma50vals.length ? dma50vals[dma50vals.length - 1].value : 0;
    const lastDma200 = dma200vals.length? dma200vals[dma200vals.length-1].value : 0;
    const bullCount  = sims.filter(s => s.bullish).length;
    const bearCount  = sims.length - bullCount;
    const finals     = sims.map(s => s.finalPrice).filter(v => Number.isFinite(v));
    const hasFinals  = finals.length > 0;
    const sortedFinals = hasFinals ? [...finals].sort((a, b) => a - b) : [];
    const minFinal   = hasFinals ? sortedFinals[0] : null;
    const maxFinal   = hasFinals ? sortedFinals[sortedFinals.length - 1] : null;
    const medFinal   = hasFinals ? sortedFinals[Math.floor(sortedFinals.length / 2)] : null;

    document.getElementById('d-stock-name').textContent = stock;
    document.getElementById('d-meta').textContent =
      `Period: ${period} · Simulation: ${days} days · Data points: ${prices.length}`;
    document.getElementById('d-candle-title').textContent = `Candlestick structure · ${formatPeriodLabel(period)}`;
    document.getElementById('d-sim-title').textContent = getSimulationTitle(days);
    document.getElementById('d-range-title').textContent = getRangeTitle(days);
    document.getElementById('d-sim-paths-title').textContent = getSimulationPathsTitle(days);
    document.getElementById('d-price').textContent    = '₹' + lastPrice.toFixed(2);
    document.getElementById('d-sim-avg').textContent  = '₹' + (oneday.averagePrice ?? 0).toFixed(2);
    document.getElementById('d-dma50').textContent    = '₹' + lastDma50.toFixed(2);
    document.getElementById('d-dma50-hint').textContent  = lastPrice > lastDma50  ? 'Price above ↑' : 'Price below ↓';
    document.getElementById('d-dma50').className      = 'kpi-value ' + (lastPrice > lastDma50  ? 'bull' : 'bear');
    document.getElementById('d-dma200').textContent   = '₹' + lastDma200.toFixed(2);
    document.getElementById('d-dma200-hint').textContent = lastPrice > lastDma200 ? 'Price above ↑' : 'Price below ↓';
    document.getElementById('d-dma200').className     = 'kpi-value ' + (lastPrice > lastDma200 ? 'bull' : 'bear');

    const bullPct = Math.round((oneday.bullishProbability ?? 0) * 100);
    const bearPct = Math.round((oneday.bearishProbability ?? 0) * 100);
    document.getElementById('d-bull-pct').textContent = bullPct + '%';
    document.getElementById('d-bear-pct').textContent = bearPct + '%';
    document.getElementById('d-upside').textContent = '+' + (((oneday.expectedUpsidePercent ?? 0) * 100).toFixed(2)) + '%';
    document.getElementById('d-downside').textContent = '−' + (((oneday.expectedDownsidePercent ?? 0) * 100).toFixed(2)) + '%';
    setTimeout(() => {
      document.getElementById('d-bull-bar').style.width = bullPct + '%';
      document.getElementById('d-bear-bar').style.width = bearPct + '%';
    }, 100);
    if (bullPct > bearPct) {
      setSignalBadge(document.getElementById('d-prob-regime'), 'Bullish edge', 'bull');
    } else if (bearPct > bullPct) {
      setSignalBadge(document.getElementById('d-prob-regime'), 'Bearish edge', 'bear');
    } else {
      setSignalBadge(document.getElementById('d-prob-regime'), 'Balanced', 'neutral');
    }
    document.getElementById('d-updown').textContent =
      `Probability spread: ${Math.abs(bullPct - bearPct)} pts · Next session leans ${bullPct === bearPct ? 'neutral' : bullPct > bearPct ? 'bullish' : 'bearish'}.`;

    document.getElementById('d-bull-count').textContent = bullCount;
    document.getElementById('d-bear-count').textContent = bearCount;
    document.getElementById('d-range-low').textContent  = hasFinals ? ('Low ₹'  + minFinal.toFixed(2)) : 'Low —';
    document.getElementById('d-range-high').textContent = hasFinals ? ('High ₹' + maxFinal.toFixed(2)) : 'High —';
    document.getElementById('d-median').textContent     = hasFinals ? ('Median: ₹' + medFinal.toFixed(2)) : 'Median: —';
    if (bullCount > bearCount) {
      setSignalBadge(document.getElementById('d-sim-bias'), 'Bull bias', 'bull');
    } else if (bearCount > bullCount) {
      setSignalBadge(document.getElementById('d-sim-bias'), 'Bear bias', 'bear');
    } else {
      setSignalBadge(document.getElementById('d-sim-bias'), 'Even split', 'neutral');
    }
    document.getElementById('d-sim-summary').textContent = sims.length
      ? `${bullCount} of ${sims.length} paths close higher over the ${days}-day run. Range spread: ${hasFinals ? ('₹' + (maxFinal - minFinal).toFixed(2)) : '—'}.`
      : `No simulation paths available for the selected ${days}-day run.`;

    const tooltipDefaults = {
      mode: 'index', intersect: false,
      backgroundColor: '#fff', titleColor: '#1a1a1a', bodyColor: '#6b6b6b',
      borderColor: '#e2e2e2', borderWidth: 1
    };
    const xAxis = {
      ticks: { autoSkip: true, maxTicksLimit: 10, font: { size: 11, family: 'IBM Plex Mono' }, color: '#9a9a9a' },
      grid: { display: false }
    };
    const yAxis = {
      ticks: { font: { size: 11, family: 'IBM Plex Mono' }, color: '#9a9a9a' },
      grid: { color: 'rgba(0,0,0,0.05)' }
    };

    const pLabels  = prices.map(p => p.date);
    const pData    = prices.map(p => p.value);
    const d50Data  = dma50vals.map(v => v.value);
    const d200Data = dma200vals.map(v => v.value);

    if (priceChartInst) priceChartInst.destroy();
    priceChartInst = new Chart(document.getElementById('priceChart'), {
      type: 'line',
      data: {
        labels: pLabels,
        datasets: [
          { label: 'Price',   data: pData,    borderColor: '#1a56db', backgroundColor: 'transparent', borderWidth: 1.5, pointRadius: 0, tension: 0.3 },
          { label: 'DMA 50',  data: d50Data,  borderColor: '#d97706', backgroundColor: 'transparent', borderWidth: 1.5, pointRadius: 0, borderDash: [4,3], tension: 0.3 },
          { label: 'DMA 200', data: d200Data, borderColor: '#dc2626', backgroundColor: 'transparent', borderWidth: 1.5, pointRadius: 0, borderDash: [6,3], tension: 0.3 }
        ]
      },
      options: {
        responsive: true, maintainAspectRatio: false,
        plugins: { legend: { display: false }, tooltip: tooltipDefaults },
        scales: { x: xAxis, y: { ...yAxis, ticks: { ...yAxis.ticks, callback: v => '₹' + v } } }
      }
    });

    candleChartState = {
      prices,
      volumes,
      dma50: dma50vals,
      dma200: dma200vals
    };
    currentCandleTimeframe = getInitialCandleTimeframe(period);
    syncCandleToolbar(currentCandleTimeframe);
    drawCandlestickChart(prices, volumes, dma50vals, dma200vals, currentCandleTimeframe);

    const simLabels  = Array.from({ length: sims[0]?.prices?.length || 30 }, (_, i) => 'D' + (i + 1));
    const simDatasets = sims.map(s => ({
      label: s.bullish ? 'Bullish' : 'Bearish',
      data: s.prices,
      borderColor: s.bullish ? 'rgba(22,163,74,0.6)' : 'rgba(220,38,38,0.5)',
      backgroundColor: 'transparent',
      borderWidth: s.bullish ? 1 : 1.2,
      pointRadius: 0,
      tension: 0.3,
      borderDash: s.bullish ? [] : [4, 3]
    }));

    if (simChartInst) simChartInst.destroy();
    simChartInst = new Chart(document.getElementById('simChart'), {
      type: 'line',
      data: { labels: simLabels, datasets: simDatasets },
      options: {
        responsive: true, maintainAspectRatio: false,
        plugins: { legend: { display: false }, tooltip: tooltipDefaults },
        scales: { x: xAxis, y: { ...yAxis, ticks: { ...yAxis.ticks, callback: v => '₹' + v.toFixed(0) } } }
      }
    });

    const vLabels = volumes.map(v => v.date);
    const vData   = volumes.map(v => v.volume / 1e6);

    if (volChartInst) volChartInst.destroy();
    volChartInst = new Chart(document.getElementById('volChart'), {
      type: 'bar',
      data: {
        labels: vLabels,
        datasets: [{
          label: 'Volume', data: vData,
          backgroundColor: 'rgba(26,86,219,0.15)', borderColor: 'rgba(26,86,219,0.4)', borderWidth: 1
        }]
      },
      options: {
        responsive: true, maintainAspectRatio: false,
        plugins: {
          legend: { display: false },
          tooltip: { ...tooltipDefaults, callbacks: { label: ctx => ctx.raw.toFixed(1) + 'M shares' } }
        },
        scales: { x: xAxis, y: { ...yAxis, ticks: { ...yAxis.ticks, callback: v => v + 'M' } } }
      }
    });

    document.getElementById('search-page').style.display = 'none';
    document.getElementById('dashboard-page').style.display = 'block';
    window.scrollTo(0, 0);
  }

  function showSearch() {
    document.getElementById('dashboard-page').style.display = 'none';
    document.getElementById('search-page').style.display = 'flex';
  }

  function setLoading(on) {
    document.getElementById('search-btn').disabled = on;
    document.getElementById('btn-spinner').style.display = on ? 'block' : 'none';
    document.getElementById('btn-text').textContent = on ? 'Fetching data...' : 'Analyse';
  }

  function showError(msg) {
    const el = document.getElementById('error-msg');
    el.textContent = msg;
    el.style.display = 'block';
  }

  function hideError() {
    document.getElementById('error-msg').style.display = 'none';
  }


  const cleanup = () => {
    formEl.removeEventListener('submit', onFormSubmit);
    stockInputEl.removeEventListener('keydown', onStockInputKeyDown);
    if (backBtnEl) backBtnEl.removeEventListener('click', showSearch);
    if (priceChartInst) priceChartInst.destroy();
    if (simChartInst) simChartInst.destroy();
    if (volChartInst) volChartInst.destroy();
  };

  return cleanup;
}

