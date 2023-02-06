--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: buys; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.buys (
    id integer NOT NULL,
    userid integer,
    registration date
);


ALTER TABLE public.buys OWNER TO postgres;

--
-- Name: TABLE buys; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.buys IS 'Покупки всех Users';


--
-- Name: buys_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.buys ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.buys_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: categorys; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categorys (
    id integer NOT NULL,
    title text
);


ALTER TABLE public.categorys OWNER TO postgres;

--
-- Name: TABLE categorys; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.categorys IS 'Категории товаров';


--
-- Name: categorys_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.categorys ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.categorys_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    title text,
    brand text,
    price double precision,
    categoryid integer,
    size double precision,
    characteristic text,
    color text
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: TABLE products; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.products IS 'Прайс-лист товаров';


--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.products ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: receipts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.receipts (
    id integer NOT NULL,
    buyid integer,
    productid integer,
    count integer
);


ALTER TABLE public.receipts OWNER TO postgres;

--
-- Name: TABLE receipts; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.receipts IS 'Таблица-связь: Количество каждой позиции(покупки) и ее данные';


--
-- Name: receipts_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.receipts ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.receipts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    firstname text,
    surname text,
    login text,
    passwd text,
    registration date,
    city text,
    country text,
    phone text,
    email text,
    role text DEFAULT 'CLIENT'::text,
    status text DEFAULT 'ACTIVE'::text
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: TABLE users; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public.users IS 'Клиенты и админы';


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: buys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.buys (id, userid, registration) FROM stdin;
\.


--
-- Data for Name: categorys; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categorys (id, title) FROM stdin;
1	Ракетки
2	Мячи
3	Кроссовки
4	Кеды
5	Куртки
6	Костюмы
7	Рюкзаки
8	Сумки
\.


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, title, brand, price, categoryid, size, characteristic, color) FROM stdin;
1	Ракетка теннисная	Wilson	25000	1	65	300.0 гр.	red
2	Ракетка теннисная	Solinco	14000	1	68	310.0 гр.	yellow
3	Ракетка теннисная	Yonex	29000	1	70	270.0 гр.	black
4	Мяч футбольный	Jogel	2500	2	65	400.0 гр.	white
5	Мяч футбольный	Addidas	16000	2	68.5	430.0 гр.	silver
6	Мяч футбольный	Nike	3499	2	70	450.0 гр.	green
7	Мяч волейбольный	Mikasa	5000	2	5	250.0 гр.	yellow-blue
8	Мяч волейбольный	Gala	3000	2	4	300.0 гр.	white
9	Мяч волейбольный	Torres	4700	2	3	250.0 гр.	green
10	Кроссовки	Puma	8700	3	41	550.0 гр.	purple
11	Кроссовки	Fila	5700	3	45	650.0 гр.	blue
12	Кроссовки	Reebok	7700	3	43	450.0 гр.	white
13	Кроссовки	Puma	8700	3	36	550.0 гр.	purple
14	Кроссовки	Fila	5700	3	38	650.0 гр.	blue
15	Кроссовки	Reebok	7700	3	39	450.0 гр.	white
16	Кеды	Puma	5700	4	36	250.0 гр.	white
17	Кеды	Fila	2700	4	38	350.0 гр.	white
18	Кеды	Reebok	4700	4	39	350.0 гр.	white
19	Куртка	Adidas	10500	5	48	0	orange
20	Куртка	Puma	15000	5	48.5	0	blue
21	Куртка	Columbia	20700	5	50	0.	blue
22	Куртка	Columbia	10700	5	54	0	green
23	Куртка	Adidas	15700	5	54.5	0	pink
24	Куртка	Puma	20700	5	56	0	blue
25	Костюм лыжный	Columbia	15500	6	48	0	orange
26	Костюм лыжный	Colmar	10000	6	48.5	0	blue
27	Костюм лыжный	Phenix	35700	6	50	0.	blue
28	Костюм лыжный	Columbia	15700	6	48	0	green
29	Костюм лыжный	Colmar	10700	6	50	0	pink
30	Костюм лыжный	Phenix	35700	6	54	0	blue
31	Рюкзак	Deuter	12500	7	48	20 л.	green
32	Рюкзак	Nike	11000	7	48.5	80 л.	black
33	Рюкзак	Naturehike	13700	7	50	40 л.	pink
34	Сумка	Columbia	2700	7	54	0	orange
35	Сумка	Adidas	5700	7	54.5	0	pink
36	Сумка	Puma	1700	7	56	0	blue
\.


--
-- Data for Name: receipts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.receipts (id, buyid, productid, count) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, firstname, surname, login, passwd, registration, city, country, phone, email, role, status) FROM stdin;
1	Ирина	Первая	i1	$2a$12$FTzWmPMTd3UV4xAWPhDhQOxRrKkwHfFRP0.ITfOCfcQ9Qm3i2WCSa	2022-01-12	Ульяновск	Россия	890614234566	i1@mail.ru	SUPERADMIN	ACTIVE
2	Нина	Вторая	n2	$2a$12$HAQsaz7iZTMizUaefqgv4.MTFhxy2njLFDT2VyX2n2X1.PJ9Hg5uy	2022-02-12	Ульяновск	Россия	890614234566	n2@mail.ru	CLIENT	ACTIVE
3	Иван	Петров	iv3	$2a$12$r74vnYszzR1ystFvaCMlHeKwd5NIbCXPY30ejON4CDiQ/UCqM9N3e	2022-03-12	Ульяновск	Россия	890614234566	iv3@mail.ru	CLIENT	ACTIVE
4	Иван	Дуров	iv4	$2a$12$OKoSVC0aNO/DUVyj5QAbKu2toeGytNVi1Zh/XHKID/5t5uQ2QFJE6	2022-04-12	Ульяновск	Россия	890614234566	iv4@mail.ru	CLIENT	ACTIVE
\.


--
-- Name: buys_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.buys_id_seq', 1, false);


--
-- Name: categorys_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categorys_id_seq', 8, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 36, true);


--
-- Name: receipts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.receipts_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 4, true);


--
-- Name: buys buys_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buys
    ADD CONSTRAINT buys_pkey PRIMARY KEY (id);


--
-- Name: categorys categorys_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categorys
    ADD CONSTRAINT categorys_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: receipts receipts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receipts
    ADD CONSTRAINT receipts_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: buys buys_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buys
    ADD CONSTRAINT buys_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);


--
-- Name: products products_categoryid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_categoryid_fkey FOREIGN KEY (categoryid) REFERENCES public.categorys(id);


--
-- Name: receipts receipts_buyid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receipts
    ADD CONSTRAINT receipts_buyid_fkey FOREIGN KEY (buyid) REFERENCES public.buys(id);


--
-- Name: receipts receipts_productid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.receipts
    ADD CONSTRAINT receipts_productid_fkey FOREIGN KEY (productid) REFERENCES public.products(id) ON DELETE SET NULL;


--
-- PostgreSQL database dump complete
--

