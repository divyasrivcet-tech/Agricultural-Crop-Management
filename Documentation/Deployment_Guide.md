# Deployment Guide (Docker / Render)

## Before deployment

- Confirm the application works locally.
- Use a dedicated PostgreSQL database.
- Back up any existing database before applying schema changes.
- Do not deploy with real farmer personal data until authentication, authorization, HTTPS and an appropriate privacy/security review are in place.

## Docker

Build the image from the repository root:

```bash
docker build -t agriculture-crop-management .
```

Run it with database environment variables:

```bash
docker run --rm -p 8080:8080 \
  -e DB_URL="jdbc:postgresql://HOST:5432/agriculture_db" \
  -e DB_USERNAME="DB_USER" \
  -e DB_PASSWORD="DB_PASSWORD" \
  -e PORT=8080 \
  agriculture-crop-management
```

The PostgreSQL server must be reachable from the container. Do not use `localhost` for a database running outside the container; use the database host provided by your database service.

## Render (Docker web service)

1. Push the project to a GitHub repository.
2. In Render, create a new Web Service and connect the repository.
3. Select Docker as the runtime so Render uses the repository Dockerfile.
4. Add environment variables:
   - `DB_URL`: JDBC URL for the PostgreSQL database.
   - `DB_USERNAME`: database username.
   - `DB_PASSWORD`: database password.
   - `PORT`: use the port provided by the platform if it is exposed as an environment variable.
5. Deploy and review build/runtime logs.
6. Open the service URL and check the home page and `/api/health`.

The PostgreSQL database must be provisioned separately and reachable from the web service. Configure the schema in that database before using the application.

## Important limitation

The current project does not include authentication or role-based access. Do not expose sensitive or real personal data on a public deployment until appropriate access controls, validation, monitoring and privacy protections are implemented.
