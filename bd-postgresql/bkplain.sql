--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.9
-- Dumped by pg_dump version 9.4.9
-- Started on 2016-09-01 10:53:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2024 (class 1262 OID 16393)
-- Name: taskdb; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE taskdb WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


ALTER DATABASE taskdb OWNER TO postgres;

\c taskdb

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 16508)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2027 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 174 (class 1259 OID 16396)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    email character varying(45) NOT NULL,
    username character varying(45) NOT NULL,
    password character(32) NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16394)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users_id_seq OWNER TO postgres;

--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 173
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- TOC entry 178 (class 1259 OID 16429)
-- Name: wi_log; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE wi_log (
    id integer NOT NULL,
    work_item_id integer NOT NULL,
    change_date date NOT NULL,
    changed_status integer NOT NULL
);


ALTER TABLE wi_log OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 16427)
-- Name: wi_log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE wi_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE wi_log_id_seq OWNER TO postgres;

--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 177
-- Name: wi_log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE wi_log_id_seq OWNED BY wi_log.id;


--
-- TOC entry 176 (class 1259 OID 16413)
-- Name: work_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE work_item (
    id integer NOT NULL,
    name character varying(45) NOT NULL,
    users_id integer NOT NULL,
    description text,
    status integer NOT NULL,
    estimated_effort time without time zone NOT NULL,
    effort time without time zone
);


ALTER TABLE work_item OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16411)
-- Name: work_item_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE work_item_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE work_item_id_seq OWNER TO postgres;

--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 175
-- Name: work_item_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE work_item_id_seq OWNED BY work_item.id;


--
-- TOC entry 1894 (class 2604 OID 16449)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- TOC entry 1896 (class 2604 OID 16450)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wi_log ALTER COLUMN id SET DEFAULT nextval('wi_log_id_seq'::regclass);


--
-- TOC entry 1895 (class 2604 OID 16451)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY work_item ALTER COLUMN id SET DEFAULT nextval('work_item_id_seq'::regclass);


--
-- TOC entry 2015 (class 0 OID 16396)
-- Dependencies: 174
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, email, username, password) FROM stdin;
\.


--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 173
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 2, true);


--
-- TOC entry 2019 (class 0 OID 16429)
-- Dependencies: 178
-- Data for Name: wi_log; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY wi_log (id, work_item_id, change_date, changed_status) FROM stdin;
\.


--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 177
-- Name: wi_log_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('wi_log_id_seq', 17, true);


--
-- TOC entry 2017 (class 0 OID 16413)
-- Dependencies: 176
-- Data for Name: work_item; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY work_item (id, name, users_id, description, status, estimated_effort, effort) FROM stdin;
\.


--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 175
-- Name: work_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('work_item_id_seq', 4, true);


--
-- TOC entry 1898 (class 2606 OID 16401)
-- Name: id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT id PRIMARY KEY (id);


--
-- TOC entry 1902 (class 2606 OID 16434)
-- Name: wi_log_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY wi_log
    ADD CONSTRAINT wi_log_id PRIMARY KEY (id);


--
-- TOC entry 1900 (class 2606 OID 16421)
-- Name: work_item_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY work_item
    ADD CONSTRAINT work_item_id PRIMARY KEY (id);


--
-- TOC entry 1903 (class 2606 OID 16422)
-- Name: users_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY work_item
    ADD CONSTRAINT users_id FOREIGN KEY (users_id) REFERENCES users(id);


--
-- TOC entry 1904 (class 2606 OID 16435)
-- Name: work_item_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY wi_log
    ADD CONSTRAINT work_item_id FOREIGN KEY (work_item_id) REFERENCES work_item(id);


--
-- TOC entry 2026 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-09-01 10:53:42

--
-- PostgreSQL database dump complete
--

