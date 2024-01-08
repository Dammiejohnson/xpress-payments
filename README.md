# XpressPayments

## Introduction

XpressPayments is a Java Spring Boot application that provides user authentication using JWT and purchase airtime payments through the Xpress Payment Airtime VTU API. 
Please read the content below to understand the application.

## Table of Contents

1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Configuration](#configuration)
3. [Authentication](#authentication)
    - [Obtaining a Token](#obtaining-a-token)
4. [Airtime Payments](#airtime-payments)
    - [Endpoint Access](#endpoint-access)
    - [Token Requirements](#token-requirements)
5. [Unit Tests](#unit-tests)
    - [Access Secured Endpoint Without Token](#access-secured-endpoint-without-token)
    - [Access Airtime Purchase Endpoint With Valid Token](#access-airtime-purchase-endpoint-with-valid-token)
    - [Calculate HMAC512](#calculate-hmac512)

## Getting Started

### Prerequisites

Ensure you have the following installed:

- [Java](https://www.java.com/) (version 17)
- [Your preferred IDE](https://www.jetbrains.com/idea/) (e.g., IntelliJ, Eclipse)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Dammiejohnson/xpress-payments.git

2. Navigate to the project directory to the path of the cloned repository:

   ```bash
   cd XpressPayments

## PostgreSQL Installation and Setup

1. Download and install PostgreSQL from [official website](https://www.postgresql.org/download/).
2. Follow the installation instructions for your operating system.
3. Start the PostgreSQL server.

## Database Configuration

Configure the PostgreSQL database connection in your Spring Boot application:

- **URL:** `jdbc:postgresql://localhost:5432/your_database_name`
- **Username:** `your_username`
- **Password:** `your_password`

Create a new database for your application using the following SQL commands:

    ```sql
    CREATE DATABASE your_database_name;


### Configuration

Adjust other configuration settings in application.yml or applications.properties to set up your database and other environment variables.

## Authentication

### Obtaining a Token

Send a POST request to the `/api/v1/auth/signUp` endpoint with valid credentials to create a user.
To obtain an authentication token, send a POST request to the `/api/v1/auth/logIn` endpoint with created User credentials to log in the user:

    
    Token expiry is 45mins

## Airtime Payments

### Purchase Airtime

Access the airtime purchase endpoint by making a POST request to `/api/v1/airtime/purchase`. Ensure you include a valid JWT token in the request header.

### Token Requirements

Include the JWT token in the request header as follows:

   ```bash
   Authorization: Bearer <token-from-login>
   ```

## Unit Tests
Unit tests were carried out using Junit5 to test the repository, service and the hash generation.
The Controller endpoints were tested via PostMan

### Access Secured Endpoint Without Token

Ensure that attempting to access secured endpoints without a token or with an expired token results in a 403 Unauthorized status.

### Access Airtime Purchase Endpoint With Valid Token

Verify that accessing the airtime purchase endpoint with a valid token returns an (200) OK status.


## Documentation
Click [here](https://documenter.getpostman.com/view/19838025/2s9YsJBXZU) to view API Documentation 
