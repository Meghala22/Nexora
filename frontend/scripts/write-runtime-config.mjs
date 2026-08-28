import { mkdirSync, writeFileSync } from 'node:fs';

const rawApiUrl = (process.env.NEXORA_API_URL || '').trim();
const isLocalApiUrl = /^https?:\/\/(localhost|127\.0\.0\.1|0\.0\.0\.0|\[::1\])(?::|\/|$)/i.test(rawApiUrl);
const apiUrl = isLocalApiUrl ? '' : rawApiUrl;
mkdirSync('public', { recursive: true });
writeFileSync('public/runtime-config.js', `window.NEXORA_API_URL = ${JSON.stringify(apiUrl)};\n`);
