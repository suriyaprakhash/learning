# FROM nginx:alpine
# Changing to nginx-unprivileged to avoid permission issue ein oc
FROM nginxinc/nginx-unprivileged 
COPY . /usr/share/nginx/html

# RUN  touch /var/run/nginx.pid && \
#      chown -R nginx:nginx /var/cache/nginx /var/run/nginx.pid
# USER nginx
# COPY --chown=nginx:nginx my/html/files /usr/share/nginx/html
# COPY --chown=nginx:nginx config/myapp/default.conf /etc/nginx/conf.d/default.conf