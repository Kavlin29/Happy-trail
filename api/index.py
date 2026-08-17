import os
from app import app, db, init_sample_data

# Initialize database lazily on Vercel
db_initialized = False

@app.before_request
def initialize_database_on_first_request():
    global db_initialized
    if not db_initialized:
        if (os.getenv('DATABASE_URL') and 'postgresql' in os.getenv('DATABASE_URL', '')) or (os.getenv('VERCEL') == '1' and not os.getenv('DATABASE_URL')):
            try:
                db.create_all()
                if os.getenv('VERCEL') == '1' and not os.getenv('DATABASE_URL'):
                    init_sample_data()
            except Exception as e:
                app.logger.warning(f"Could not initialize database: {e}")
        db_initialized = True

# Vercel entrypoint
# Note: We must export `app` directly at the top level. We must NOT define a variable named `handler`
# because Vercel expects `handler` to be a BaseHTTPRequestHandler class, not a WSGI app instance.
