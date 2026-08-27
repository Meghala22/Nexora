export type Role = 'RELATIONSHIP_MANAGER' | 'OPERATIONS_MAKER' | 'APPROVER' | 'ADMIN' | 'AUDITOR';
export type Severity = 'critical' | 'high' | 'medium' | 'low';
export interface Customer { id: string; backendId?: string; name: string; industry: string; manager: string; risk: string; stage: string; products: number; status: string; updated: string; }
export interface Approval { id: string; customer: string; change: string; requestedBy: string; risk: Severity; submitted: string; sla: string; status: string; }
export interface EventRecord { id: string; type: string; customer: string; topic: string; partition: number; offset: number; status: string; retries: number; timestamp: string; correlation: string; }
