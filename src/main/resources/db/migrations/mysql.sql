CREATE TABLE redirect
(
    id        BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    link_from TEXT                  NOT NULL,
    link_to   TEXT                  NOT NULL
);

CREATE TABLE redirect_history
(
    id          BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    redirect_id BIGINT,
    link_from   TEXT,
    link_to     TEXT,
    ip          TEXT,
    headers     TEXT,
    cookies     TEXT,
    body        TEXT,
    time        TIMESTAMP
);