"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";

export default function AppTopBar() {
  const pathname = usePathname();

  const analysisActive = pathname === "/";
  const detailsActive = pathname === "/stockdetails" || pathname.startsWith("/stockdetails/");

  return (
    <header className="app-topbar">
      <div className="app-topbar-inner">
        <div className="app-brand">
          <div className="brand-mark">M</div>
          <div className="brand-copy">
            <div className="brand-name">Stock Tools</div>
            <div className="brand-tag">Simulation and search workspace</div>
          </div>
        </div>
        <nav className="app-nav" aria-label="Service switcher">
          <span className="nav-label">Services</span>
          <Link className={`nav-link ${analysisActive ? "active" : ""}`} href="/" aria-current={analysisActive ? "page" : undefined}>
            Stock Simulation
          </Link>
          <Link className={`nav-link ${detailsActive ? "active" : ""}`} href="/stockdetails" aria-current={detailsActive ? "page" : undefined}>
            Stock search
          </Link>
        </nav>
        <div className="nav-utility" aria-label="App status">
          <span className="utility-chip">API Ready</span>
          <span className="utility-chip utility-chip-soft">Live workspace</span>
        </div>
      </div>
    </header>
  );
}
