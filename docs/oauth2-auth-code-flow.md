# OAuth2 + OpenID Connect авторизація (з PKCE)

Цей документ детально пояснює процес авторизації користувача через Authorization Code Flow з [PKCE](oauth2-pkce.md) для
отримання токена доступу.

---

## Генерація PKCE кодів

[Детальніше що таке PKCE, коли та навіщо він треба](oauth2-pkce.md)

```javascript
function base64URLEncode(buffer) {
    return btoa(String.fromCharCode.apply(null, new Uint8Array(buffer)))
        .replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');
}

async function sha256(plain) {
    const encoder = new TextEncoder();
    const data = encoder.encode(plain);
    return await crypto.subtle.digest('SHA-256', data);
}

async function generatePKCECodes() {
    const array = new Uint32Array(32);
    window.crypto.getRandomValues(array);
    const codeVerifier = base64URLEncode(array);
    const hashed = await sha256(codeVerifier);
    const codeChallenge = base64URLEncode(hashed);

    console.log("Code Verifier:", codeVerifier);
    console.log("Code Challenge:", codeChallenge);
}
```

> Цей скрипт можна виконати в консолі браузера

---

## Запит на `authorization_code`

```text
GET https://AUTH_SERVER_DOMAIN/authorize?
  response_type=code
  &client_id=CLIENT_ID
  &redirect_uri=http://localhost/callback
  &scope=openid profile email
  &code_challenge=CODE_CHALLENGE
  &code_challenge_method=S256
```

Після входу буде редірект на:

```
http://localhost/callback?code=AUTHORIZATION_CODE
```

---

## Обмін `authorization_code` на `access_token`

```bash
curl -X POST "https://AUTH_SERVER_DOMAIN/oauth/token" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=authorization_code" \
  -d "client_id=CLIENT_ID" \
  -d "code=AUTHORIZATION_CODE" \
  -d "redirect_uri=http://localhost/callback" \
  -d "code_verifier=CODE_VERIFIER"
```

### Приклад відповіді:

```json
{
  "access_token": "eyJhbGciOiJIUz...",
  "id_token": "eyJhbGciOiJSUz...",
  "token_type": "Bearer",
  "expires_in": 86400
}
```

---

## Використання `access_token`

```http
GET /api/v1/products
Authorization: Bearer ACCESS_TOKEN
```

---

## Що таке `.well-known/openid-configuration`

Це URL, що вказує на OpenID конфігурацію сервера (впринципі є стандартом, тому
в [application.yaml](../src/main/resources/application.yaml) достатньо вказати
`http://localhost:9090/realms/owu-ecommerce` для `issuer-uri`):

```
http://localhost:9090/realms/owu-ecommerce/.well-known/openid-configuration
```

Він повертає JSON з:

- `authorization_endpoint`
- `token_endpoint`
- `userinfo_endpoint`
- `jwks_uri` — URL відкритого ключа для перевірки токенів
- `scopes_supported`, `grant_types_supported`, тощо

---

## Додатково

- `access_token` часто є **JWT**: можна валідувати без запитів до сервера (якщо є `jwks_uri`)
- `refresh_token` використовується для оновлення `access_token` (тільки в бекенді)
- PKCE — обов’язковий для SPA, захищає від перехоплення коду авторизації

---

## Рекомендації

- Використовуй PKCE для браузерних/мобільних клієнтів
- Не зберігай `client_secret` в клієнтському коді
- Завжди використовуй HTTPS (якщо це лише локально, то HTTP підійде)
- Застосовуй `scope=openid` якщо потрібна автентифікація користувача

---