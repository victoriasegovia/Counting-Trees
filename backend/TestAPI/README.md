# TestAPI Suite

This folder contains the automated API testing suite for the Counting Trees Backend.

## Contents

- `test_api.sh`: Main Bash script to execute tests.
- `../testApiLogs`: Directory where test logs are saved (created automatically).

## Prerequisites

- **Git Bash** (or any Bash environment) must be installed.
- **Backend Application** must be running on `http://localhost:8080`.
- **Database** must be accessible by the backend.

## How to Run

1.  Open your terminal inside this folder (`backend/TestAPI`).
2.  Run the script:
    ```bash
    bash test_api.sh
    ```
3.  The script will:
    -   Register a new test user.
    -   Login to retrieve a JWT Token.
    -   Perform CRUD operations on all entities (Users, Plants, Species, Alerts, etc.).
    -   Generate a Markdown log file in `../testApiLogs`.

## How to Update

If you add a new Controller or Endpoint:

1.  Open `test_api.sh`.
2.  Locate the relevant section (e.g., `# --- 3. SPECIES ---`).
3.  Add a new `perform_request` call:
    ```bash
    perform_request "Description of Test" "METHOD" "/endpoint/url" "JSON_BODY_IF_POST_PUT" EXPECTED_HTTP_CODE "$TOKEN"
    ```
4.  If the endpoint requires a new dependency (e.g., a new entity ID), ensure you extract that ID from a previous response using `grep` and `sed`.

## Troubleshooting

- **403 Forbidden:** Check `SecurityConfig.java` to ensure the endpoint is permitted or the user has the correct role.
- **Connection Refused:** Ensure the backend is running.
- **jq errors:** The script uses `grep` and `sed` to avoid `jq` dependency, but ensure your JSON responses are standard.
