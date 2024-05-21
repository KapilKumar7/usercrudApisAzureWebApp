## Application is deployed on Azure webapp


User API Documentation  
This document provides an overview and detailed usage instructions for the User API, including endpoints for creating, retrieving, and deleting user accounts. The API also includes a health check endpoint.

Endpoints
1. Create User Account
Endpoint: /account/save

Method: POST

Request Body:

{
  "firstName": "string",
  "lastName": "string",
  "email": "string"
}

Response:

200 OK: User account created successfully.
400 Bad Request: Validation error or other issues.

Example :

curl -X POST "https://userapis.azurewebsites.net/v1/api/users/account/save" \
-H "Content-Type: application/json" \
-d '{
  "firstName": "Mohit",
  "lastName": "Kumar",
  "email": "mohit@zoop.one"
}'


2. Get User Account
Endpoint: /account/get

Method: GET

Query Parameter:

firstName (required): First name of the user to retrieve.
Response:

200 OK: User account retrieved successfully.
400 Bad Request: User not found or other issues.
Example:

curl -X GET "https://userapis.azurewebsites.net/v1/api/users/account/get?firstName=Mohit"


3. Delete User Account
Endpoint: /account/delete

Method: DELETE

Query Parameter:

firstName (required): First name of the user to delete.
Response:

200 OK: User account deleted successfully.
400 Bad Request: User not found or other issues.
Example:
curl -X DELETE "https://userapis.azurewebsites.net/v1/api/users/account/delete?firstName=Mohit"


4. Health Check For service Running Health
Endpoint: /healthCheck

Method: GET

Response:

200 OK: "Health Check is Success"
Example:

curl -X GET "https://userapis.azurewebsites.net/v1/api/users/healthCheck"
