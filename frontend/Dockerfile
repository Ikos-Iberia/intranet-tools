# =========================
# 1️⃣ Development stage
# =========================
FROM node:lts AS development

WORKDIR /app

# Copiar solo package.json y package-lock.json para cachear dependencias
COPY package*.json ./

# Instalar dependencias completas para desarrollo
RUN npm install --legacy-peer-deps

# Copiar todo el código
COPY . .

# Variables de entorno para desarrollo
ENV NODE_ENV=development
ENV PORT=3000

# Exponer puerto de desarrollo
EXPOSE 3000

# Comando para desarrollo con hot-reload
CMD ["npm", "run", "dev"]

# =========================
# 2️⃣ Build stage
# =========================
FROM development AS build

# Construir la app para producción
RUN npm run build

# =========================
# 3️⃣ Production stage
# =========================
FROM node:lts AS production

WORKDIR /app

# Copiar solo lo necesario del build stage
COPY --from=build /app/package*.json ./
COPY --from=build /app/node_modules ./node_modules
COPY --from=build /app/.next ./
COPY --from=build /app/public ./public

# Variables de entorno para producción
ENV NODE_ENV=production
ENV PORT=3000

# Exponer puerto de producción
EXPOSE 3000

# Comando para producción
CMD ["npm", "start"]