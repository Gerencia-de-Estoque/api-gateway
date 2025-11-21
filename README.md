# API Gateway

Spring Cloud Gateway usado para unificar o acesso ao serviço de autenticação e à API da loja. Ele roteia:

- `/auth/**` → serviço de autenticação (`AUTH_SERVICE_URL`)
- `/api/**` → API da loja (`LOJA_SERVICE_URL`)

## Pré‑requisitos

- Docker e Docker Compose v2
- Serviços de destino rodando (ex.: `api-autentic` na porta 8089 e `API-LOJA-DE-CONSTRU-O` na porta 8090)

## Variáveis

| Variável | Descrição | Padrão |
| --- | --- | --- |
| `AUTH_SERVICE_URL` | URL base do serviço de autenticação | `http://localhost:8089` |
| `LOJA_SERVICE_URL` | URL base da API da loja | `http://localhost:8089` |
| `PORT` | Porta do gateway | `8080` |
| `CORS_ALLOWED_ORIGINS` | Lista de origens permitidas (`*` para todas) | `*` |

Defina `AUTH_SERVICE_URL=http://localhost:8089` e `LOJA_SERVICE_URL=http://localhost:8090` para usar os serviços atuais.

## Execução

```bash
cd api-gateway
AUTH_SERVICE_URL=http://localhost:8089 \
LOJA_SERVICE_URL=http://localhost:8090 \
docker compose up -d --build
```

- Gateway: http://localhost:8091 (conforme `ports` do compose)
- Adminer interno: http://localhost:8083 (opcional, apenas para depuração caso use o MySQL local do compose)

Finalize com `docker compose down`.

## Testes e Lint

O gateway roda checkstyle, PMD, SpotBugs e os testes. Use (com saída detalhada de cada teste):

```bash
cd api-gateway/springboot/demo
./mvnw -Dstyle.color=always \
       -Dsurefire.reportFormat=plain \
       -Dsurefire.printSummary=true \
       -Dsurefire.useFile=false \
       verify
```

Se algum plugin ou teste falhar, o comando retorna erro e exibe os detalhes no console.

## Fluxo típico

1. Frontend fala apenas com o Gateway (`http://localhost:8091`).
2. Requests `POST /auth/login` são encaminhadas ao serviço de autenticação.
3. Requests `GET/POST /api/**` são redirecionadas à API da loja, preservando headers como `Authorization`.
