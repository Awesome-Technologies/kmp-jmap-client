# Authenticated Examples

All examples in this repository must use authenticated requests.

## 0. Koin composition root (selected DI framework)

```kotlin
val jmapModule = module {
    single<HeaderProvider> {
        HeaderProvider {
            mapOf(
                "Authorization" to "Bearer ${tokenProvider.currentAccessToken()}",
                "Accept" to "application/json"
            )
        }
    }
    single { createJmapClient(endpoint = "https://example.com/jmap/api/", headerProvider = get()) }
}
```

## 1. Client bootstrap with caller-owned headers

```kotlin
val client: JmapClient = createJmapClient(
    endpoint = "https://example.com/jmap/api/",
    headerProvider = HeaderProvider {
        mapOf(
            "Authorization" to "Bearer ${tokenProvider.currentAccessToken()}",
            "Accept" to "application/json"
        )
    }
)
```

## 2. Session bootstrap and capability check

```kotlin
val session = sessionProvider.get()
require(session.capabilities.containsKey("urn:ietf:params:jmap:mail")) {
    "Server does not advertise mail capability"
}
```

## 3. Typed mail request

```kotlin
val mailboxes = client.call(
    method = MailboxGet(
        accountId = accountId,
        ids = null,
        properties = listOf("id", "name", "role", "totalEmails")
    )
)
```

## 4. Batch request with deterministic call ids

```kotlin
val batch = BatchRequest(
    using = setOf(
        CapabilityId.Core,
        CapabilityId.Mail
    ),
    methodCalls = listOf(
        MethodCallEnvelope(
            callId = "c1",
            name = "Mailbox/get",
            arguments = jsonObjectOf("accountId" to accountId)
        ),
        MethodCallEnvelope(
            callId = "c2",
            name = "Email/query",
            arguments = jsonObjectOf("accountId" to accountId)
        )
    )
)

val result = client.batch(batch)
```

## 5. Raw escape hatch for unsupported extensions

```kotlin
val raw = client.raw(
    RawJmapRequest(
        using = listOf("urn:ietf:params:jmap:core", "urn:example:vendor:feature"),
        methodCalls = listOf(
            RawMethodCall(
                name = "VendorThing/get",
                arguments = jsonObjectOf("accountId" to accountId),
                callId = "v1"
            )
        )
    )
)
```

## 6. Draft-gated usage pattern

```kotlin
val hasCalendarDraft = session.capabilities.containsKey("urn:ietf:params:jmap:calendars")
if (hasCalendarDraft && featureFlags.calendarDraftEnabled) {
    // call draft-backed calendar module
}
```

## Security notes

- Do not log bearer tokens or refresh tokens.
- Keep token refresh logic in the host app.
- Recreate push subscriptions when session/webpush key material changes.
