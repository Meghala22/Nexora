create table entitlement_requests (
  id varchar(36) primary key, version bigint not null default 0, customer_id varchar(36) not null,
  product varchar(120) not null, permission varchar(120) not null, justification text not null,
  requested_by varchar(255) not null, requested_at timestamp with time zone not null default current_timestamp,
  status varchar(40) not null, risk_level varchar(32) not null, correlation_id varchar(64) not null
);
create index idx_entitlement_requests_customer on entitlement_requests(customer_id, requested_at desc);
create index idx_entitlement_requests_status on entitlement_requests(status, requested_at asc);

create table approval_actions (
  id varchar(36) primary key, request_id varchar(36) not null references entitlement_requests(id),
  actor varchar(255) not null, action varchar(40) not null, comment text, occurred_at timestamp with time zone not null default current_timestamp
);
create index idx_approval_actions_request on approval_actions(request_id, occurred_at asc);

create table event_failures (
  id varchar(36) primary key, event_id varchar(64) not null unique, event_type varchar(80) not null,
  entity_id varchar(64) not null, correlation_id varchar(64) not null, error_reason text not null,
  retry_count integer not null default 0, last_attempt_at timestamp with time zone not null default current_timestamp,
  payload text not null, status varchar(32) not null
);
