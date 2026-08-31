import { Approval, Customer, EventRecord } from './models';

export const customers: Customer[] = [
  {id:'CUST-001248',name:'ACME Corporation',industry:'Industrial Manufacturing',manager:'Sarah Mitchell',risk:'Medium',stage:'Entitlements',products:5,status:'Active',updated:'12 min ago'},
  {id:'CUST-001315',name:'Globex Industries',industry:'Energy & Utilities',manager:'David Chen',risk:'High',stage:'KYC Review',products:3,status:'In review',updated:'28 min ago'},
  {id:'CUST-001102',name:'Stark Industries',industry:'Technology',manager:'Sarah Mitchell',risk:'Low',stage:'Activated',products:6,status:'Active',updated:'1 hr ago'},
  {id:'CUST-001289',name:'Wayne Enterprises',industry:'Financial Services',manager:'Priya Shah',risk:'Medium',stage:'Approval',products:4,status:'Pending',updated:'2 hrs ago'},
  {id:'CUST-001074',name:'Umbrella Holdings',industry:'Healthcare',manager:'David Chen',risk:'High',stage:'Due Diligence',products:2,status:'Exception',updated:'Yesterday'},
  {id:'CUST-001196',name:'Initech',industry:'Business Services',manager:'Priya Shah',risk:'Low',stage:'Product Setup',products:3,status:'Active',updated:'Yesterday'},
  {id:'CUST-001422',name:'Pioneer Logistics Group',industry:'Transportation & Logistics',manager:'Sarah Mitchell',risk:'Medium',stage:'Activated',products:5,status:'Active',updated:'2 days ago'},
  {id:'CUST-001433',name:'Summit Foods International',industry:'Food & Agriculture',manager:'David Chen',risk:'Low',stage:'Activated',products:4,status:'Active',updated:'3 days ago'},
  {id:'CUST-001451',name:'Harborview Real Estate Trust',industry:'Commercial Real Estate',manager:'Priya Shah',risk:'High',stage:'Submitted',products:1,status:'In review',updated:'4 days ago'},
  {id:'CUST-001467',name:'Northstar Medical Supplies',industry:'Healthcare Distribution',manager:'Sarah Mitchell',risk:'Medium',stage:'KYC Review',products:2,status:'In review',updated:'5 days ago'},
  {id:'CUST-001482',name:'Evergreen Manufacturing Co',industry:'Industrial Manufacturing',manager:'David Chen',risk:'Low',stage:'Entitlements',products:3,status:'Pending',updated:'1 week ago'}
];
export const approvals: Approval[] = [
  {id:'REQ-49218',customer:'ACME Corporation',change:'Wire Transfer Release',requestedBy:'Alex Morgan',risk:'critical',submitted:'Today, 10:18 AM',sla:'1h 42m',status:'Awaiting review'},
  {id:'REQ-49211',customer:'Wayne Enterprises',change:'ACH approval threshold',requestedBy:'Sam Taylor',risk:'high',submitted:'Today, 9:41 AM',sla:'3h 19m',status:'Awaiting review'},
  {id:'REQ-49189',customer:'Globex Industries',change:'KYC risk classification',requestedBy:'Sarah Mitchell',risk:'medium',submitted:'Yesterday',sla:'Met',status:'Changes requested'}
];
export const events: EventRecord[] = [
  {id:'EVT-849201',type:'ENTITLEMENT_ACTIVATED',customer:'ACME Corporation',topic:'entitlement-events',partition:2,offset:184920,status:'PROCESSED',retries:0,timestamp:'10:42:18',correlation:'CORR-849201'},
  {id:'EVT-849198',type:'ENTITLEMENT_REQUESTED',customer:'ACME Corporation',topic:'entitlement-events',partition:2,offset:184918,status:'DUPLICATE',retries:0,timestamp:'10:19:41',correlation:'CORR-849201'},
  {id:'EVT-849177',type:'CUSTOMER_UPDATED',customer:'Umbrella Holdings',topic:'customer-events',partition:0,offset:77218,status:'FAILED',retries:3,timestamp:'09:54:06',correlation:'CORR-849177'}
];
