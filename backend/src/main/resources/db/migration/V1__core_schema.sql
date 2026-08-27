create table customers (
  id varchar(36) primary key, version bigint not null default 0, legal_name varchar(255) not null,
  customer_number varchar(32) not null unique, industry varchar(120), risk_level varchar(32),
  onboarding_stage varchar(64), updated_at timestamp with time zone not null
);
create table processed_events (
  event_id varchar(64) primary key, event_type varchar(80) not null, correlation_id varchar(64) not null,
  entity_id varchar(64) not null, processed_at timestamp with time zone not null default current_timestamp, status varchar(32) not null
);
create table audit_events (
  id bigint generated always as identity primary key, actor varchar(160) not null, actor_role varchar(64) not null,
  action varchar(80) not null, entity_type varchar(80) not null, entity_id varchar(64) not null,
  before_state text, after_state text, correlation_id varchar(64) not null, occurred_at timestamp with time zone not null default current_timestamp
);
create index idx_audit_events_entity on audit_events(entity_type, entity_id, occurred_at desc);
create index idx_processed_events_correlation on processed_events(correlation_id);
