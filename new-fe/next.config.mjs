/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return [
      {
        source: "/api/:path*",
        destination: "http://user-service:8085/api/v1/:path*",
      },
    ];
  },
};

export default nextConfig;
