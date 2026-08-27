import { mkdirSync, writeFileSync } from 'node:fs';

const apiUrl = process.env.NEXORA_API_URL || 'http://localhost:8080/api/v1';
mkdirSync('public', { recursive: true });
writeFileSync('public/runtime-config.js', `window.NEXORA_API_URL = ${JSON.stringify(apiUrl)};\n`);
