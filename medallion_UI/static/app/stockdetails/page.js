"use client";

import { useEffect } from "react";
import { initStockDetailsPage } from "../../components/stockDetailsLegacy";

export default function StockDetailsPage() {
  useEffect(() => {
    const cleanup = initStockDetailsPage();
    return () => {
      if (typeof cleanup === "function") cleanup();
    };
  }, []);

  return (
    <div className="container stock-details-page">
      <div className="search-bar">
        <input type="text" id="stockInput" placeholder="Stock search (e.g. Tata Steel, Infosys...)" defaultValue="" />
        <button className="search-btn" id="searchBtn" type="button">Search</button>
      </div>

      <div id="errorBox" className="error-msg" style={{ display: "none" }}></div>
      <div id="loadingBox" className="loading" style={{ display: "none" }}>Fetching stock data...</div>
      <div id="dashboard" style={{ display: "none" }}></div>
    </div>
  );
}
