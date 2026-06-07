import AppTopBar from "./AppTopBar";

const globalStyles = `
  *, *::before, *::after { box-sizing: border-box; }
  html, body { margin: 0; min-height: 100%; }
  body {
    min-height: 100vh;
    font-family: 'Space Grotesk', 'Sora', system-ui, sans-serif;
    color: #eef4ff;
    background:
      radial-gradient(circle at 12% 8%, rgba(98, 244, 255, 0.18), transparent 24%),
      radial-gradient(circle at 85% 0%, rgba(74, 222, 128, 0.14), transparent 18%),
      radial-gradient(circle at 50% 120%, rgba(59, 130, 246, 0.18), transparent 28%),
      linear-gradient(145deg, #050a12 0%, #09111d 40%, #0f1729 100%);
    overflow-x: hidden;
  }
  body::before,
  body::after {
    content: '';
    position: fixed;
    inset: auto;
    border-radius: 999px;
    pointer-events: none;
    mix-blend-mode: screen;
    z-index: 0;
    filter: blur(12px);
  }
  body::before {
    width: 560px;
    height: 560px;
    top: -220px;
    right: -180px;
    background: radial-gradient(circle, rgba(98, 244, 255, 0.22) 0%, rgba(98, 244, 255, 0.04) 55%, transparent 72%);
  }
  body::after {
    width: 420px;
    height: 420px;
    left: -160px;
    bottom: -140px;
    background: radial-gradient(circle, rgba(74, 222, 128, 0.18) 0%, rgba(74, 222, 128, 0.03) 52%, transparent 70%);
  }
  body > * { position: relative; z-index: 1; }
  a { color: inherit; text-decoration: none; }
  button, input, select, textarea { font: inherit; }
  canvas { display: block; }

  .app-shell { min-height: 100vh; }
  .app-topbar {
    width: 100%;
    position: sticky;
    top: 0;
    z-index: 10;
    margin: 0;
    padding: 14px 0;
    border-radius: 0;
    background: rgba(7, 14, 26, 0.72);
    border-bottom: 1px solid rgba(122, 194, 255, 0.16);
    box-shadow: 0 18px 44px rgba(0, 0, 0, 0.34);
    backdrop-filter: blur(18px);
  }
  .app-topbar-inner {
    width: min(1360px, calc(100vw - 32px));
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 16px;
  }
  .app-brand { display: flex; align-items: center; gap: 12px; min-width: 0; }
  .brand-mark {
    width: 38px;
    height: 38px;
    border-radius: 12px;
    display: grid;
    place-items: center;
    background: linear-gradient(135deg, rgba(44,230,255,.96) 0%, rgba(59,130,246,.92) 55%, rgba(139,92,246,.92) 100%);
    color: #04101f;
    font-weight: 800;
    box-shadow: 0 16px 28px rgba(59, 130, 246, 0.28);
  }
  .brand-copy { min-width: 0; }
  .brand-name { font-size: 15px; font-weight: 800; letter-spacing: 0.1em; text-transform: uppercase; color: #eef4ff; }
  .brand-tag { font-size: 11px; color: #9db0cb; margin-top: 2px; }
  .app-nav { display: flex; align-items: center; gap: 10px; flex-wrap: wrap; }
  .nav-label { font-size: 10px; text-transform: uppercase; letter-spacing: 0.18em; color: #9db0cb; font-weight: 700; margin-right: 6px; }
  .nav-utility { display: flex; align-items: center; gap: 10px; flex-wrap: wrap; margin-left: auto; }
  .utility-chip {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    padding: 9px 14px;
    border-radius: 999px;
    border: 1px solid rgba(98, 244, 255, 0.18);
    background: rgba(12, 21, 37, 0.9);
    color: #eef4ff;
    font-size: 12px;
    font-weight: 700;
    letter-spacing: 0.02em;
  }
  .utility-chip::before {
    content: '';
    width: 8px;
    height: 8px;
    border-radius: 999px;
    background: #4ade80;
    box-shadow: 0 0 0 6px rgba(74, 222, 128, 0.14);
  }
  .utility-chip-soft {
    color: #9db0cb;
    border-color: rgba(122, 194, 255, 0.12);
    background: rgba(7, 14, 26, 0.72);
  }
  .utility-chip-soft::before {
    background: #62f4ff;
    box-shadow: 0 0 0 6px rgba(98, 244, 255, 0.12);
  }
  .nav-link {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    padding: 9px 14px;
    border-radius: 999px;
    border: 1px solid rgba(122, 194, 255, 0.18);
    background: rgba(7, 14, 26, 0.88);
    color: #eef4ff;
    transition: transform .18s ease, background .18s ease, border-color .18s ease, box-shadow .18s ease;
  }
  .nav-link:hover { transform: translateY(-1px); border-color: rgba(98, 244, 255, 0.5); box-shadow: 0 0 0 4px rgba(98, 244, 255, 0.08); }
  .nav-link.active { background: linear-gradient(135deg, rgba(44,230,255,.95) 0%, rgba(59,130,246,.96) 52%, rgba(139,92,246,.96) 100%); color: #04101f; border-color: transparent; }
  .nav-link small { opacity: 0.72; font-size: 10px; letter-spacing: 0.08em; text-transform: uppercase; }
  .nav-link.active small { opacity: 0.88; }

  #search-page,
  #dashboard-page,
  .stock-details-page {
    width: min(1360px, calc(100vw - 32px));
    margin: 0 auto;
  }

  #search-page {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 40px 16px;
  }

  #dashboard-page,
  .stock-details-page {
    padding: 24px 0 40px;
  }

  .app-main { padding-bottom: 24px; }

  .wordmark {
    font-size: 11px;
    letter-spacing: 0.4em;
    text-transform: uppercase;
    color: #62f4ff;
    text-shadow: 0 0 18px rgba(98, 244, 255, 0.25);
    margin-bottom: 18px;
  }

  .hero-title,
  .stock-name,
  .dash-stock-name {
    font-family: 'Sora', 'Space Grotesk', sans-serif;
    font-weight: 700;
    letter-spacing: -0.04em;
    background: linear-gradient(135deg, #ffffff 0%, #b9d9ff 42%, #67f0ff 100%);
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent !important;
  }

  .hero-title { font-size: clamp(2.8rem, 7vw, 5.2rem); line-height: 0.95; margin: 0 0 10px; text-align: center; }
  .hero-sub { margin: 0 0 26px; color: #9db0cb; text-align: center; font-size: 15px; }

  .search-form, .card, .kpi, .metric-card, .chart-card, .search-bar, .signal-metric, .signal-foot-item, .sim-stat, .range-pill, .stock-header, .quick-stats, .tabs, .candle-wrap {
    background: rgba(10, 18, 32, 0.78);
    border: 1px solid rgba(122, 194, 255, 0.14);
    box-shadow: 0 18px 44px rgba(0, 0, 0, 0.32);
    backdrop-filter: blur(18px);
  }

  .search-form, .card, .chart-card, .stock-header, .search-bar, .metric-card, .kpi, .sim-stat, .signal-metric, .signal-foot-item, .range-pill { border-radius: 22px; }

  .search-form { width: min(700px, 100%); padding: 18px; display: flex; flex-direction: column; gap: 14px; }
  .field-row { display: grid; grid-template-columns: repeat(3, minmax(0, 1fr)); gap: 12px; }
  .field { display: flex; flex-direction: column; gap: 6px; }
  .field label { font-size: 10px; text-transform: uppercase; letter-spacing: 0.14em; color: #9db0cb; font-weight: 700; }
  .field input, .field select, .search-bar input {
    width: 100%;
    background: rgba(7, 14, 26, 0.92);
    color: #eef4ff;
    border: 1px solid rgba(122, 194, 255, 0.22);
    border-radius: 14px;
    padding: 12px 14px;
    outline: none;
    transition: box-shadow .2s ease, border-color .2s ease, transform .2s ease, background .2s ease;
  }
  .field input::placeholder, .search-bar input::placeholder { color: rgba(157, 176, 203, 0.66); }
  .field input:focus, .field select:focus, .search-bar input:focus { border-color: rgba(98, 244, 255, 0.72); box-shadow: 0 0 0 4px rgba(98, 244, 255, 0.12), 0 0 28px rgba(98, 244, 255, 0.08); }

  .search-btn, .view-details-btn {
    background: linear-gradient(135deg, #2ce6ff 0%, #3b82f6 52%, #8b5cf6 100%);
    color: #04101f;
    border: none;
    border-radius: 14px;
    padding: 12px 16px;
    font-weight: 700;
    cursor: pointer;
    box-shadow: 0 16px 34px rgba(59, 130, 246, 0.24);
    transition: transform .18s ease, filter .18s ease, box-shadow .18s ease;
  }
  .search-btn:hover, .view-details-btn:hover { transform: translateY(-2px); filter: brightness(1.05); }
  .search-btn:disabled { background: #506173; color: #c9d4e2; cursor: not-allowed; box-shadow: none; }

  #dashboard-page { display: block; }
  .dash-header, .stock-header, .chart-header-modern, .signal-head, .sim-range-head, .quick-stats, .chart-header { display: flex; gap: 14px; justify-content: space-between; flex-wrap: wrap; }
  .dash-header, .stock-header { align-items: flex-start; }
  .dash-header { margin-bottom: 18px; padding-bottom: 16px; border-bottom: 1px solid rgba(157, 176, 203, 0.14); }
  .stock-header { margin-bottom: 18px; padding: 20px; }

  .dash-meta, .stock-industry, .price-time, .metric-label, .metric-sub, .section-label, .card-title, .card-kicker, .chart-title, .news-date, .news-summary, .sim-stat-label, .sim-range-head, .signal-label, .signal-foot-label, .quick-stat-label, .kpi-label, .kpi-hint, .legend, .prob-label, .news-headline, .analyst-label, .tech-indicator-label, .stock-codes, td:first-child { color: #9db0cb !important; }
  .kpi-value, .metric-value, .sim-stat-val, .signal-value, .current-price, .quick-stat-value, .tech-indicator-value, .prob-val, td:last-child, .news-headline, .dash-stock-name { color: #eef4ff !important; }

  .kpi-grid, .metrics-grid, .two-col, .signal-grid, .signal-foot, .sim-stats, .range-pills { display: grid; gap: 12px; }
  .kpi-grid { grid-template-columns: repeat(4, minmax(0, 1fr)); margin-bottom: 18px; }
  .metrics-grid { grid-template-columns: repeat(auto-fill, minmax(180px, 1fr)); }
  .two-col, .signal-grid, .signal-foot, .sim-stats, .range-pills { grid-template-columns: repeat(2, minmax(0, 1fr)); }
  .row { display: flex; gap: 14px; margin-bottom: 14px; }
  .col { flex: 1; min-width: 0; }

  .kpi, .metric-card, .sim-stat, .signal-metric, .signal-foot-item, .range-pill { position: relative; overflow: hidden; padding: 14px 16px; }
  .kpi::before, .metric-card::before, .sim-stat::before, .signal-metric::before, .signal-foot-item::before, .range-pill::before { content: ''; position: absolute; inset: 0; background: linear-gradient(115deg, transparent 0%, rgba(255,255,255,0.08) 50%, transparent 100%); opacity: .16; pointer-events: none; }

  .time-chip, .timeframe-btn, .tab-btn, .back-btn { background: rgba(7, 14, 26, 0.88); color: #eef4ff; border: 1px solid rgba(122, 194, 255, 0.18); border-radius: 999px; padding: 8px 12px; cursor: pointer; transition: transform .18s ease, background .18s ease, color .18s ease, border-color .18s ease; }
  .time-chip.active, .timeframe-btn.active, .tab-btn.active { background: linear-gradient(135deg, rgba(98,244,255,.94) 0%, rgba(59,130,246,.96) 100%); color: #04101f; border-color: transparent; }

  .chart-toolbar, .tabs { display: inline-flex; gap: 6px; flex-wrap: wrap; padding: 5px; background: rgba(7, 14, 26, 0.72); border: 1px solid rgba(122, 194, 255, 0.14); border-radius: 999px; }
  .chart-tooltip { background: rgba(4, 8, 16, 0.96) !important; border: 1px solid rgba(122, 194, 255, 0.14); box-shadow: 0 20px 50px rgba(0,0,0,.45) !important; }

  .error-msg { display: none; background: rgba(251, 113, 133, 0.12); color: #fecdd3; border: 1px solid rgba(251, 113, 133, 0.22); border-radius: 14px; padding: 12px 14px; }
  .loading { color: #9db0cb; }
  .loading::after { border: 3px solid rgba(122, 194, 255, 0.12); border-top-color: #62f4ff; }

  .badge-buy, .signal-badge.bull, .up, .kpi-value.bull, .range-pill.bull { color: #4ade80 !important; }
  .badge-sell, .signal-badge.bear, .down, .kpi-value.bear, .range-pill.bear { color: #fb7185 !important; }
  .badge-hold, .signal-badge.neutral, .neutral { color: #fbbf24 !important; }

  .stock-codes span { background: rgba(98, 244, 255, 0.08); border: 1px solid rgba(98, 244, 255, 0.12); padding: 3px 8px; border-radius: 999px; }
  .stock-details-page { padding-top: 18px; }
  .stock-details-page .search-bar {
    padding: 14px;
    margin-bottom: 18px;
    display: flex;
    gap: 12px;
    align-items: center;
  }
  .stock-details-page .search-bar input { flex: 1 1 auto; min-width: 0; }
  .stock-details-page .search-bar .search-btn { min-width: 130px; }
  .stock-details-page .container { width: 100%; }
  .stock-details-page .card, .stock-details-page .chart-card, .stock-details-page .stock-header, .stock-details-page .search-bar { margin-bottom: 18px; }

  .card, .chart-card, .stock-header { border: 1px solid rgba(122, 194, 255, 0.14); }
  .card { padding: 18px; margin-bottom: 16px; }
  .chart-card { padding: 18px; }

  @media (max-width: 900px) { .field-row, .kpi-grid, .two-col, .signal-grid, .signal-foot, .sim-stats, .range-pills { grid-template-columns: 1fr 1fr; } }
  @media (max-width: 640px) {
    .app-topbar { padding: 10px 0; }
    .app-topbar-inner { width: min(100vw - 16px, 100%); flex-direction: column; align-items: flex-start; }
    .app-nav { width: 100%; }
    .nav-link { flex: 1 1 0; justify-content: center; }
    #search-page, #dashboard-page, .stock-details-page { width: min(100vw - 20px, 100%); }
    .hero-title { font-size: clamp(2.2rem, 12vw, 3.6rem); }
    .field-row, .kpi-grid, .two-col, .signal-grid, .signal-foot, .sim-stats, .range-pills, .metrics-grid { grid-template-columns: 1fr; }
    .row { flex-direction: column; }
    .search-form, .card, .chart-card, .stock-header { border-radius: 18px; }
  }
`;

export const metadata = {
  title: "Medallion",
  description: "Medallion is a futuristic stock intelligence app with simulations, charts, and detailed market analytics"
};

export default function RootLayout({ children }) {
  return (
    <html lang="en">
      <body>
        <style dangerouslySetInnerHTML={{ __html: globalStyles }} />
        <div className="app-shell">
          <AppTopBar />
          <main className="app-main">{children}</main>
        </div>
      </body>
    </html>
  );
}
