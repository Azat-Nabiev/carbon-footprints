# Build process by pulling the latest Node.js image
FROM node:latest AS builder
WORKDIR /app
# Copies the package.json and package-lock.json
COPY package*.json ./
# Installs all the dependencies defined in package*.json
RUN npm install
# Copies all other files and directories from the project's root
# into the '/app' working directory
COPY . .
# Builds the application
RUN npm run build

# Starts a new stage using the smaller image
FROM node:alpine
WORKDIR /app
# Copies only the package.json and package-lock.json files
# from the "builder" stage
COPY --from=builder /app/package*.json ./
# Installs only used dependencies
RUN npm install --only=production
# Copies the built application files and public directory
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/public ./public
# Command for starting
CMD ["npm", "start"]
