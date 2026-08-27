-- Demo identities have company-issued addresses. Non-demo identities are self-registered and begin read-only.
update users set role = 'AUDITOR' where email not like '%@nexora.demo';
