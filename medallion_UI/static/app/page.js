"use client";

import { useEffect } from "react";
import { initHomePage } from "../components/homeLegacy";

export default function HomePage() {
  useEffect(() => {
    const cleanup = initHomePage();
    return () => {
      if (typeof cleanup === "function") cleanup();
    };
  }, []);

  return (
    <>
      <div id="search-page">
        <div className="wordmark">Medallion</div>
        <h1 className="hero-title">Stock Simulation</h1>
        <p className="hero-sub">Monte Carlo simulations &amp; price forecasting</p>

        <form className="search-form" id="search-form" onSubmit={(e) => e.preventDefault()}>
          <div className="field">
            <label>Stock name</label>
            <input type="text" id="inp-stock" placeholder="e.g. Tata Steel, Infosys, Reliance" required autoComplete="off" />
          </div>
          <div className="field-row">
            <div className="field">
              <label>Period</label>
              <select id="inp-period" defaultValue="6m">
                <option value="1m">1 Month</option>
                <option value="3m">3 Months</option>
                <option value="6m">6 Months</option>
                <option value="1yr">1 Year</option>
                <option value="2yr">2 Years</option>
                <option value="5yr">5 Years</option>
              </select>
            </div>
            <div className="field">
              <label>Simulation days</label>
              <input type="number" id="inp-days" defaultValue="30" min="1" max="365" placeholder="30" />
            </div>
            <div className="field">
              <label>Simulation Count</label>
              <select id="inp-simulation_count" defaultValue="500">
                <option value="10">10</option>
                <option value="100">100</option>
                <option value="500">500</option>
                <option value="1000">1000</option>
                <option value="10000">10000</option>
                <option value="100000">100000</option>
              </select>
            </div>
          </div>
          <div className="error-msg" id="error-msg"></div>
          <button className="search-btn" id="search-btn" type="submit">
            <span className="spinner" id="btn-spinner"></span>
            <span id="btn-text">Analyse</span>
          </button>
        </form>
      </div>

      <div id="dashboard-page" style={{ display: "none" }}>
        <div className="dash-header">
          <div>
            <div className="dash-stock-name" id="d-stock-name">-</div>
            <div className="dash-meta" id="d-meta">-</div>
          </div>
          <button className="back-btn" id="back-btn" type="button">&larr; New search</button>
        </div>
        <div className="dash-header">
            <div> <a id="stockDetailsLink" className="view-details-btn" href="#">
          ↗ View Stock Details
            </a></div>
        </div>

       

        <div className="kpi-grid">
          <div className="kpi">
            <div className="kpi-label">Current price</div>
            <div className="kpi-value accent" id="d-price">-</div>
            <div className="kpi-hint">Last close</div>
          </div>
          <div className="kpi">
            <div className="kpi-label">1-day sim avg</div>
            <div className="kpi-value" id="d-sim-avg">-</div>
          </div>
          <div className="kpi">
            <div className="kpi-label">DMA 50</div>
            <div className="kpi-value" id="d-dma50">-</div>
            <div className="kpi-hint" id="d-dma50-hint">-</div>
          </div>
          <div className="kpi">
            <div className="kpi-label">DMA 200</div>
            <div className="kpi-value" id="d-dma200">-</div>
            <div className="kpi-hint" id="d-dma200-hint">-</div>
          </div>
        </div>

        <div className="card">
          <div className="card-title">Price history with moving averages</div>
          <div className="legend">
            <span className="leg-item"><span className="leg-dot" style={{ background: "#1a56db" }}></span>Price</span>
            <span className="leg-item"><span className="leg-dot" style={{ background: "#d97706" }}></span>DMA 50</span>
            <span className="leg-item"><span className="leg-dot" style={{ background: "#dc2626" }}></span>DMA 200</span>
          </div>
          <div style={{ position: "relative", width: "100%", height: "240px" }}>
            <canvas id="priceChart"></canvas>
          </div>
        </div>

        <div className="card">
          <div className="chart-header-modern">
            <div>
              <div className="card-kicker">Market structure</div>
              <div className="card-title" id="d-candle-title">Candlestick structure</div>
            </div>
            <div className="chart-toolbar" id="candle-toolbar">
              <button className="time-chip" type="button" data-tf="1m">1M</button>
              <button className="time-chip" type="button" data-tf="3m">3M</button>
              <button className="time-chip active" type="button" data-tf="6m">6M</button>
              <button className="time-chip" type="button" data-tf="1y">1Y</button>
              <button className="time-chip" type="button" data-tf="all">All</button>
            </div>
          </div>
          <div className="legend">
            <span className="leg-item"><span className="leg-dot" style={{ background: "#15803d", height: "10px", width: "10px", borderRadius: "50%" }}></span>Bull candles</span>
            <span className="leg-item"><span className="leg-dot" style={{ background: "#b91c1c", height: "10px", width: "10px", borderRadius: "50%" }}></span>Bear candles</span>
            <span className="leg-item"><span className="leg-dot" style={{ background: "#8b5cf6" }}></span>DMA 50</span>
            <span className="leg-item"><span className="leg-dot" style={{ background: "#f59e0b" }}></span>DMA 200</span>
          </div>
          <div className="candle-wrap">
            <div className="candle-price-stage">
              <canvas id="candleChart"></canvas>
              <div className="chart-tooltip" id="candleTooltip" style={{ display: "none" }}></div>
            </div>
            <div className="candle-volume-stage">
              <canvas id="candleVolumeChart"></canvas>
            </div>
          </div>
        </div>

        <div className="two-col">
          <div className="card signal-card" style={{ marginBottom: 0 }}>
            <div className="signal-head">
              <div>
                <div className="card-kicker">Signal engine</div>
                <div className="card-title" id="d-prob-title">1-day probability pulse</div>
              </div>
              <div className="signal-badge neutral" id="d-prob-regime">Balanced</div>
            </div>
            <div className="signal-grid">
              <div className="signal-metric bull-panel">
                <div className="signal-label">Bullish</div>
                <div className="signal-value bull" id="d-bull-pct">-</div>
                <div className="signal-track"><div className="signal-fill bull" id="d-bull-bar" style={{ width: "0%" }}></div></div>
              </div>
              <div className="signal-metric bear-panel">
                <div className="signal-label">Bearish</div>
                <div className="signal-value bear" id="d-bear-pct">-</div>
                <div className="signal-track"><div className="signal-fill bear" id="d-bear-bar" style={{ width: "0%" }}></div></div>
              </div>
            </div>
            <div className="signal-foot">
              <div className="signal-foot-item">
                <span className="signal-foot-label">Upside vector</span>
                <span className="signal-foot-value" id="d-upside">-</span>
              </div>
              <div className="signal-foot-item">
                <span className="signal-foot-label">Downside vector</span>
                <span className="signal-foot-value" id="d-downside">-</span>
              </div>
            </div>
            <div className="signal-summary" id="d-updown">-</div>
          </div>

          <div className="card signal-card" style={{ marginBottom: 0 }}>
            <div className="signal-head">
              <div>
                <div className="card-kicker">Monte Carlo</div>
                <div className="card-title" id="d-sim-title">30-day simulation outlook</div>
              </div>
              <div className="signal-badge neutral" id="d-sim-bias">Awaiting data</div>
            </div>
            <div className="sim-stats">
              <div className="sim-stat">
                <div className="sim-stat-label">Bullish paths</div>
                <div className="sim-stat-val" style={{ color: "var(--bull)" }} id="d-bull-count">-</div>
              </div>
              <div className="sim-stat">
                <div className="sim-stat-label">Bearish paths</div>
                <div className="sim-stat-val" style={{ color: "var(--bear)" }} id="d-bear-count">-</div>
              </div>
            </div>
            <div className="sim-range-head">
              <span id="d-range-title">30-day final price corridor</span>
              <span id="d-median">-</span>
            </div>
            <div className="range-pills">
              <span className="range-pill bear" id="d-range-low">-</span>
              <span className="range-pill bull" id="d-range-high">-</span>
            </div>
            <div className="signal-summary" id="d-sim-summary">-</div>
          </div>
        </div>

        <div className="card" style={{ marginTop: "1rem" }}>
          <div className="card-title" id="d-sim-paths-title">Monte Carlo simulation paths</div>
          <div className="legend">
            <span className="leg-item"><span className="leg-dot" style={{ background: "#16a34a" }}></span>Bullish paths</span>
            <span className="leg-item"><span className="leg-dot" style={{ background: "#dc2626" }}></span>Bearish paths</span>
          </div>
          <div style={{ position: "relative", width: "100%", height: "220px" }}>
            <canvas id="simChart"></canvas>
          </div>
        </div>

        <div className="card">
          <div className="card-title">Daily volume (NSE)</div>
          <div style={{ position: "relative", width: "100%", height: "150px" }}>
            <canvas id="volChart"></canvas>
          </div>
        </div>
      </div>
    </>
  );
}
