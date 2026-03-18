#!/bin/bash

echo "Starting BuildLedger Microservices..."

# Start Eureka Server
echo "Starting Eureka Server (PORT 8761)..."
cd buildledger-eureka-server && mvn spring-boot:run &
EUREKA_PID=$!
echo "Waiting for Eureka to initialize..."
sleep 15

# Start API Gateway
echo "Starting API Gateway (PORT 8080)..."
cd ../buildledger-api-gateway && mvn spring-boot:run &
GATEWAY_PID=$!
echo "Waiting for Gateway to initialize..."
sleep 15

# Start IAM Service
echo "Starting IAM Service (PORT 8081)..."
cd ../buildledger-iam/iam-service && mvn spring-boot:run &
IAM_PID=$!

# Start Vendor Service
echo "Starting Vendor Service (PORT 9090)..."
cd ../../vendormanagement/Buildledger-main && mvn spring-boot:run &
VENDOR_PID=$!

# Start Contract Creation Service
echo "Starting Contract Creation Service (PORT 8082)..."
cd ../../contractmanagement/contractcreation && mvn spring-boot:run &
CONTRACT_PID=$!

# Start Delivery Service
echo "Starting Delivery Service (PORT 8084)..."
cd ../../deliverService && mvn spring-boot:run &
DELIVERY_PID=$!

# Start Compliance Audit Service
echo "Starting Compliance Audit Service (PORT 8085)..."
cd ../compliance-audit-service && mvn spring-boot:run &
COMPLIANCE_PID=$!

echo "All services are starting up in the background!"
echo "PIDs: "
echo "Eureka: $EUREKA_PID"
echo "API Gateway: $GATEWAY_PID"
echo "IAM: $IAM_PID"
echo "Vendor: $VENDOR_PID"
echo "Contract: $CONTRACT_PID"
echo "Delivery: $DELIVERY_PID"
echo "Compliance: $COMPLIANCE_PID"

echo "To stop them, press Ctrl+C or kill the process IDs above."
wait
