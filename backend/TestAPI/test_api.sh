#!/bin/bash

# Configuration
BASE_URL="http://localhost:8080/api/v1"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
LOG_DIR="testApiLogs"
LOG_FILE="$LOG_DIR/api_test_log_$TIMESTAMP.md"
EMAIL="test_${TIMESTAMP}@example.com"
PASSWORD="password123"

# Colors
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

# Create Log Directory
mkdir -p "$LOG_DIR"
echo "# API Test Log - $TIMESTAMP" > "$LOG_FILE"
echo "Base URL: $BASE_URL" >> "$LOG_FILE"
echo "" >> "$LOG_FILE"

# Helper Function to Log and Execute
perform_request() {
    local DESC=$1
    local METHOD=$2
    local ENDPOINT=$3
    local DATA=$4
    local EXPECTED_CODE=$5
    local TOKEN=$6

    echo -e "\n[$METHOD] $ENDPOINT - $DESC"
    echo "## $DESC" >> "$LOG_FILE"
    echo "**Endpoint:** \`$METHOD $ENDPOINT\`" >> "$LOG_FILE"
    
    # Files
    local REQ_FILE="request_${TIMESTAMP}.json"
    local HEAD_FILE="headers_${TIMESTAMP}.txt"
    local BODY_FILE="body_${TIMESTAMP}.txt"

    # Prepare Command
    local CMD=(curl -s -X "$METHOD" "$BASE_URL$ENDPOINT" -D "$HEAD_FILE" -o "$BODY_FILE")
    
    if [ -n "$DATA" ]; then
        echo "$DATA" > "$REQ_FILE"
        CMD+=(-H "Content-Type: application/json" -d "@$REQ_FILE")
        echo -e "\n**Request Body:**\n\`\`\`json\n$DATA\n\`\`\`" >> "$LOG_FILE"
    fi

    if [ -n "$TOKEN" ]; then
        CMD+=(-H "Authorization: Bearer $TOKEN")
    fi

    # Execute
    "${CMD[@]}"
    
    # Extract HTTP Code (strip \r)
    HTTP_CODE=$(grep "HTTP/" "$HEAD_FILE" | tail -1 | awk '{print $2}' | tr -d '\r')
    
    # Read Body
    BODY=$(cat "$BODY_FILE")

    echo "**Response Code:** $HTTP_CODE (Expected: $EXPECTED_CODE)" >> "$LOG_FILE"
    echo -e "**Response Body:**\n\`\`\`json\n$BODY\n\`\`\`" >> "$LOG_FILE"
    echo "---" >> "$LOG_FILE"

    if [ "$HTTP_CODE" -eq "$EXPECTED_CODE" ]; then
        echo -e "${GREEN}PASS${NC} $HTTP_CODE"
    else
        echo -e "${RED}FAIL${NC} Got $HTTP_CODE, Expected $EXPECTED_CODE"
    fi
    
    # Cleanup
    rm -f "$REQ_FILE" "$HEAD_FILE" "$BODY_FILE"

    # Return Body
    echo "$BODY"
}

# --- 1. AUTHENTICATION ---
echo "--- 1. Authentication ---"
REGISTER_BODY="{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"$EMAIL\",\"password\":\"$PASSWORD\",\"role\":\"ADMIN\"}"
perform_request "Register User" "POST" "/auth/register" "$REGISTER_BODY" 200 "" > /dev/null

LOGIN_BODY="{\"email\":\"$EMAIL\",\"password\":\"$PASSWORD\"}"
LOGIN_RES=$(perform_request "Login User" "POST" "/auth/authenticate" "$LOGIN_BODY" 200 "")
# Extract Token and Sanitize \r from Windows curl output
TOKEN=$(echo "$LOGIN_RES" | sed -n 's/.*"token":"\([^"]*\)".*/\1/p' | tr -d '\r\n')
echo "DEBUG TOKEN LINE: '$TOKEN'"

if [ -z "$TOKEN" ]; then
    echo -e "${RED}CRITICAL: No Token received. Exiting.${NC}"
    exit 1
fi

# --- 2. USERS ---
echo "--- 2. Users ---"
USER_LIST=$(perform_request "Get All Users" "GET" "/users" "" 200 "$TOKEN")
USER_ID=$(echo "$USER_LIST" | grep -o '"userId":[0-9]*' | tail -1 | cut -d':' -f2 | tr -d '\r')

if [ -n "$USER_ID" ]; then
    perform_request "Get User $USER_ID" "GET" "/users/$USER_ID" "" 200 "$TOKEN" > /dev/null
    
    UPDATE_USER_BODY="{\"firstName\":\"Updated\",\"lastName\":\"User\",\"email\":\"$EMAIL\",\"password\":\"$PASSWORD\",\"role\":\"ADMIN\"}"
    perform_request "Update User $USER_ID" "PUT" "/users/$USER_ID" "$UPDATE_USER_BODY" 200 "$TOKEN" > /dev/null
    
    # Testing Profiles
    perform_request "Get Welcome" "GET" "/users/welcome" "" 200 "" > /dev/null
    perform_request "Get Admin Profile" "GET" "/users/admin/adminProfile" "" 200 "$TOKEN" > /dev/null
fi

# --- 3. SPECIES ---
echo "--- 3. Species ---"
# FIXED: Specie uses commonName and scientificName (must be unique)
SPECIE_BODY="{\"commonName\":\"Oak\",\"scientificName\":\"Quercus_${TIMESTAMP}\",\"description\":\"Big Tree\"}"
perform_request "Create Specie" "POST" "/species" "$SPECIE_BODY" 200 "$TOKEN" > /dev/null
SPECIE_LIST=$(perform_request "Get All Species" "GET" "/species" "" 200 "$TOKEN")
SPECIE_ID=$(echo "$SPECIE_LIST" | grep -o '"specieId":[0-9]*' | tail -1 | cut -d':' -f2 | tr -d '\r')

if [ -n "$SPECIE_ID" ]; then
    perform_request "Get Specie $SPECIE_ID" "GET" "/species/$SPECIE_ID" "" 200 "$TOKEN" > /dev/null
    UPD_SPECIE="{\"commonName\":\"Old Oak\",\"scientificName\":\"Quercus_Old_${TIMESTAMP}\",\"description\":\"Very Big Tree\"}"
    perform_request "Update Specie $SPECIE_ID" "PUT" "/species/$SPECIE_ID" "$UPD_SPECIE" 200 "$TOKEN" > /dev/null
fi

# --- 4. ALERT TYPES ---
echo "--- 4. Alert Types ---"
# AlertType uses name (unique)
ATYPE_BODY="{\"name\":\"Pest_${TIMESTAMP}\",\"description\":\"Insects\"}"
perform_request "Create AlertType" "POST" "/alert-types" "$ATYPE_BODY" 200 "$TOKEN" > /dev/null
ATYPE_LIST=$(perform_request "Get All AlertTypes" "GET" "/alert-types" "" 200 "$TOKEN")
ATYPE_ID=$(echo "$ATYPE_LIST" | grep -o '"alertTypeId":[0-9]*' | tail -1 | cut -d':' -f2 | tr -d '\r')

if [ -n "$ATYPE_ID" ]; then
    perform_request "Get AlertType $ATYPE_ID" "GET" "/alert-types/$ATYPE_ID" "" 200 "$TOKEN" > /dev/null
    UPD_ATYPE="{\"name\":\"Severe Pest_${TIMESTAMP}\",\"description\":\"Bad Insects\"}"
    perform_request "Update AlertType $ATYPE_ID" "PUT" "/alert-types/$ATYPE_ID" "$UPD_ATYPE" 200 "$TOKEN" > /dev/null
fi

# --- 5. HEALTH STATUSES ---
echo "--- 5. Health Statuses ---"
# HealthStatus uses name (unique)
HEALTH_BODY="{\"name\":\"Good_${TIMESTAMP}\",\"description\":\"Healthy\"}"
perform_request "Create HealthStatus" "POST" "/health-statuses" "$HEALTH_BODY" 200 "$TOKEN" > /dev/null
HEALTH_LIST=$(perform_request "Get All HealthStatuses" "GET" "/health-statuses" "" 200 "$TOKEN")
HEALTH_ID=$(echo "$HEALTH_LIST" | grep -o '"statusId":[0-9]*' | tail -1 | cut -d':' -f2 | tr -d '\r')

if [ -n "$HEALTH_ID" ]; then
    perform_request "Get HealthStatus $HEALTH_ID" "GET" "/health-statuses/$HEALTH_ID" "" 200 "$TOKEN" > /dev/null
    UPD_HEALTH="{\"name\":\"Excellent_${TIMESTAMP}\",\"description\":\"Very Healthy\"}"
    perform_request "Update HealthStatus $HEALTH_ID" "PUT" "/health-statuses/$HEALTH_ID" "$UPD_HEALTH" 200 "$TOKEN" > /dev/null
fi

# --- 6. PLANT ---
echo "--- 6. Plant ---"
if [ -z "$SPECIE_ID" ] || [ -z "$USER_ID" ]; then
     echo -e "${RED}Skipping Plant tests due to missing dependencies (Specie: $SPECIE_ID, User: $USER_ID)${NC}"
else
    # Plant needs Specie and User
    # Using correct DTO structure
    PLANT_BODY="{\"latitude\":10.0,\"longitude\":20.0,\"specie\":{\"specieId\":$SPECIE_ID},\"plantedBy\":{\"userId\":$USER_ID},\"plantVerificationStatus\":\"PENDING\"}"
    perform_request "Create Plant" "POST" "/plants" "$PLANT_BODY" 200 "$TOKEN" > /dev/null
    
    PLANT_LIST=$(perform_request "Get All Plants" "GET" "/plants" "" 200 "$TOKEN")
    PLANT_ID=$(echo "$PLANT_LIST" | grep -o '"plantId":[0-9]*' | tail -1 | cut -d':' -f2 | tr -d '\r')
    
    if [ -n "$PLANT_ID" ]; then
        perform_request "Get Plant $PLANT_ID" "GET" "/plants/$PLANT_ID" "" 200 "$TOKEN" > /dev/null
        UPD_PLANT="{\"latitude\":11.0,\"longitude\":21.0,\"specie\":{\"specieId\":$SPECIE_ID},\"plantedBy\":{\"userId\":$USER_ID},\"plantVerificationStatus\":\"APPROVED\"}"
        perform_request "Update Plant $PLANT_ID" "PUT" "/plants/$PLANT_ID" "$UPD_PLANT" 200 "$TOKEN" > /dev/null

        # --- 7. NOTE ---
        echo "--- 7. Note ---"
        NOTE_BODY="{\"content\":\"Test note\",\"plant\":{\"plantId\":$PLANT_ID}}"
        perform_request "Create Note" "POST" "/notes" "$NOTE_BODY" 200 "$TOKEN" > /dev/null
        NOTE_LIST=$(perform_request "Get All Notes" "GET" "/notes" "" 200 "$TOKEN")
        NOTE_ID=$(echo "$NOTE_LIST" | grep -o '"noteId":[0-9]*' | tail -1 | cut -d':' -f2 | tr -d '\r')
        
        if [ -n "$NOTE_ID" ]; then
            perform_request "Get Note $NOTE_ID" "GET" "/notes/$NOTE_ID" "" 200 "$TOKEN" > /dev/null
            UPD_NOTE="{\"content\":\"Updated note\",\"plant\":{\"plantId\":$PLANT_ID}}"
            perform_request "Update Note $NOTE_ID" "PUT" "/notes/$NOTE_ID" "$UPD_NOTE" 200 "$TOKEN" > /dev/null
            perform_request "Delete Note $NOTE_ID" "DELETE" "/notes/$NOTE_ID" "" 200 "$TOKEN" > /dev/null
        fi

        # --- 8. ALERT ---
        echo "--- 8. Alert ---"
        if [ -n "$ATYPE_ID" ]; then
            # Needs alertTypeId
            ALERT_BODY="{\"description\":\"Alert!\",\"plant\":{\"plantId\":$PLANT_ID},\"alertType\":{\"alertTypeId\":$ATYPE_ID}}"
            perform_request "Create Alert" "POST" "/alerts" "$ALERT_BODY" 200 "$TOKEN" > /dev/null
            ALERT_LIST=$(perform_request "Get All Alerts" "GET" "/alerts" "" 200 "$TOKEN")
            ALERT_ID=$(echo "$ALERT_LIST" | grep -o '"alertId":[0-9]*' | tail -1 | cut -d':' -f2 | tr -d '\r')

            if [ -n "$ALERT_ID" ]; then
                perform_request "Get Alert $ALERT_ID" "GET" "/alerts/$ALERT_ID" "" 200 "$TOKEN" > /dev/null
                UPD_ALERT="{\"description\":\"Resolved Alert\",\"plant\":{\"plantId\":$PLANT_ID},\"alertType\":{\"alertTypeId\":$ATYPE_ID}}"
                perform_request "Update Alert $ALERT_ID" "PUT" "/alerts/$ALERT_ID" "$UPD_ALERT" 200 "$TOKEN" > /dev/null
                perform_request "Delete Alert $ALERT_ID" "DELETE" "/alerts/$ALERT_ID" "" 200 "$TOKEN" > /dev/null
            fi
        fi
        
        # --- 9. PHOTO ---
        echo "--- 9. Photo ---"
        PHOTO_BODY="{\"url\":\"http://test.com/img.jpg\",\"plant\":{\"plantId\":$PLANT_ID}}"
        perform_request "Create Photo" "POST" "/photos" "$PHOTO_BODY" 200 "$TOKEN" > /dev/null
        PHOTO_LIST=$(perform_request "Get All Photos" "GET" "/photos" "" 200 "$TOKEN")
        PHOTO_ID=$(echo "$PHOTO_LIST" | grep -o '"photoId":[0-9]*' | tail -1 | cut -d':' -f2 | tr -d '\r')
        
        if [ -n "$PHOTO_ID" ]; then
             perform_request "Get Photo $PHOTO_ID" "GET" "/photos/$PHOTO_ID" "" 200 "$TOKEN" > /dev/null
             perform_request "Get Photos for Plant $PLANT_ID" "GET" "/photos/plants/$PLANT_ID" "" 200 "$TOKEN" > /dev/null
             perform_request "Delete Photo $PHOTO_ID" "DELETE" "/photos/$PHOTO_ID" "" 200 "$TOKEN" > /dev/null
        fi

        # Delete Plant (Cleanup)
        perform_request "Delete Plant $PLANT_ID" "DELETE" "/plants/$PLANT_ID" "" 200 "$TOKEN" > /dev/null
    fi
fi

# Cleanup Independent Entities
echo "--- Cleanup ---"
if [ -n "$SPECIE_ID" ]; then perform_request "Delete Specie $SPECIE_ID" "DELETE" "/species/$SPECIE_ID" "" 200 "$TOKEN" > /dev/null; fi
if [ -n "$ATYPE_ID" ]; then perform_request "Delete AlertType $ATYPE_ID" "DELETE" "/alert-types/$ATYPE_ID" "" 204 "$TOKEN" > /dev/null; fi # Usually 204 for delete
if [ -n "$HEALTH_ID" ]; then perform_request "Delete HealthStatus $HEALTH_ID" "DELETE" "/health-statuses/$HEALTH_ID" "" 200 "$TOKEN" > /dev/null; fi

echo -e "\n${GREEN}Test Run Complete.${NC} Logs saved to $LOG_FILE"
