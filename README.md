# two sites one server

    # Build the js
    lein cljsbuild once

    # Build the CSS
    lein garden once

    # Run server on port 3000
    # add hostnames to /etc/hosts to get the different sites
    # in: http://misc.local:3000/
    # and: http://bfa.local:3000/
    lein run

    # With the server running, you can start figwheel to do
    # live-coding
    lein figwheel im-dev bfa-dev

    # Or use the alias
    lein fig-all

    # With figwheel running, you can automatically update the CSS
    lein garden auto
