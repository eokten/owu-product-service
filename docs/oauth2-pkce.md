# Що таке PKCE (Proof Key for Code Exchange)?

**PKCE (Proof Key for Code Exchange)** — це розширення до OAuth 2.0, що забезпечує **додатковий захист публічних
клієнтів** (SPA, мобільні додатки) від атак на перехоплення `authorization_code`.

---

## Навіщо потрібен PKCE?

### Без PKCE:

1. Клієнт надсилає користувача на авторизацію.
2. Отримує `authorization_code` через `redirect_uri`.
3. Якщо зловмисник перехопить цей код — він може обміняти його на `access_token`.

### З PKCE:

1. Клієнт генерує випадковий `code_verifier`.
2. Обчислює з нього хеш (`code_challenge`) і відправляє у запиті `/authorize`.
3. Коли клієнт отримує `authorization_code`, він надсилає `code_verifier` у запиті `/token`.
4. Authorization Server звіряє: `code_verifier` → `code_challenge`. Якщо все сходиться — видає `access_token`.

> Якщо хтось перехопить `authorization_code`, але не має `code_verifier` — токен не отримає.

---

## Чому це важливо?

Оскільки публічні клієнти (в браузері або на мобільному) **не можуть зберігати client_secret**, PKCE виконує роль
**доведення, що саме цей клієнт починав авторизацію**.

---
