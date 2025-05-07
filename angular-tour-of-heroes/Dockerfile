FROM node:22.15.0-alpine
WORKDIR /app
COPY package*.json .
RUN npm i -g npm@latest && npm ci --legacy-peer-deps
COPY . .
CMD ["npm", "run", "start", "--", "--host", "0.0.0.0", "--poll", "1000"]
