const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:8080/medallion";

async function postJson(path, payload, includeCredentials = false) {
  const response = await fetch(`${API_BASE_URL}${path}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    credentials: includeCredentials ? "include" : "same-origin",
    body: JSON.stringify(payload)
  });

  if (!response.ok) {
    throw new Error(`Server returned ${response.status}`);
  }

  return response.json();
}

export async function getMedallionData(payload) {
  return postJson("/getMedallion", payload, true);
}

export async function getStockDetailsData(payload) {
  return postJson("/getStock", payload);
}

export { API_BASE_URL };
