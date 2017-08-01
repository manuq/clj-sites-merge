# two sites one repo

    # Build the js
    lein cljsbuild once

    # Build the CSS
    lein garden once

    # Run site A on port 3000
    lein run

    # Run site B on port 3001
    lein run 3001

    # Run site A with figwheel
    lein with-profile +fig-im figwheel im-dev

    # Or use the alias
    lein fig-im

    # Run site B with figwheel
    lein with-profile +fig-bfa figwheel bfa-dev

    # Or use the alias
    lein fig-bfa
