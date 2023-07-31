#
## Model
www.msaez.io/#/storming/newtest



```
   http :8082/companies name="uengine" foundedDate="2017-03-15" industry="SW"
   http :8082/companies name="google" foundedDate="2017-03-15" industry="SW"
   http :8082/companies name="tesla" industry="manufacturing" 

http :8082/companies/search/findByCompanyQuery name=="uengine" industry=="SW"
http :8082/companies/search/findByCompanyQuery name=="uengine"
http :8082/companies/search/findByCompanyQuery industry=="SW"

   http :8082/companies/search/findByCompanyQuery industry=="SW" sort=="name"
   http :8082/companies/search/findByCompanyQuery industry=="SW" sort=="name" page==0 size==1
 http :8082/companies/search/findByCompanyQuery industry=="SW" sort=="name" page==1 size==1

```

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- basic
- sales


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- basic
```
 http :8088/companies name="name" industry="industry" foundedDate="foundedDate" 
```
- sales
```
 http :8088/salesOrders id="id" salesPerson="salesPerson" salesType="salesType" salesItem="salesItem" companyId="companyId" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```


Sales DDLs
```
--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

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

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: sales_order_sales_item; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sales_order_sales_item (
    sales_order_id bigint NOT NULL,
    sales_item_price double precision NOT NULL,
    sales_item_product_id character varying(255),
    sales_item_quantity integer NOT NULL
);


ALTER TABLE public.sales_order_sales_item OWNER TO postgres;

--
-- Name: sales_order_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sales_order_table (
    id bigint NOT NULL,
    company_id_id character varying(255),
    sales_person character varying(255),
    sales_type integer
);


ALTER TABLE public.sales_order_table OWNER TO postgres;

--
-- Name: sales_order_table sales_order_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales_order_table
    ADD CONSTRAINT sales_order_table_pkey PRIMARY KEY (id);


--
-- Name: sales_order_sales_item fkklvj5olqnkcr3b38tqk0d0aak; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales_order_sales_item
    ADD CONSTRAINT fkklvj5olqnkcr3b38tqk0d0aak FOREIGN KEY (sales_order_id) REFERENCES public.sales_order_table(id);


```