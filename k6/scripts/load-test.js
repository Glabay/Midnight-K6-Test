import http from 'k6/http';
import { check } from 'k6';

const BASE_URL = __ENV.BASE_URL || 'http://host.docker.internal:80';
const REQUESTS_FILE = __ENV.REQUESTS_FILE || '/config/requests.json';

const requests = JSON.parse(open(REQUESTS_FILE)).requests;

export const options = {
    scenarios: {
        load: {
            executor: 'ramping-arrival-rate',

            // Start at 50 iterations per second.
            startRate: 50,
            timeUnit: '1s',

            // VU capacity. (acceleration)
            preAllocatedVUs: 100,
            maxVUs: 500,

            stages: [
                { target: 100, duration: '30s' },
                { target: 250, duration: '30s' },
                { target: 500, duration: '30s' },
                { target: 1500, duration: '30s' },
                { target: 2500, duration: '30s' },

                // Hold the peak load instead of immediately
                // beginning the ramp-down.
                { target: 2500, duration: '1m' },

                { target: 0, duration: '10s' },
            ],
        },
    },

    thresholds: {
        // Less than 1% of HTTP requests should fail.
        http_req_failed: ['rate<0.01'],

        // 95% of HTTP requests should complete within 400 ms.
        http_req_duration: ['p(95)<400'],

        // At least 99% of our explicit checks should pass.
        checks: ['rate>0.99'],

        // Every scheduled iteration should be started.
        dropped_iterations: ['count==0'],
    },
};

export default function () {
    for (const request of requests) {
        const url = `${BASE_URL}${request.path}`;

        const params = {
            headers: {
                'Content-Type': 'application/json',
            },
            tags: {
                endpoint: request.path,
                method: request.method,
            },
        };

        const body =
            request.body !== undefined
                ? JSON.stringify(request.body)
                : null;

        const response = http.request(
            request.method,
            url,
            body,
            params
        );

        check(response, {
            [`${request.method} ${request.path} succeeded`]: (r) =>
                r.status >= 200 && r.status < 400,
        });
    }
}