PGDMP     5    9                z           postgres    14.5    14.4                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    13754    postgres    DATABASE     f   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Ecuador.1252';
    DROP DATABASE postgres;
                postgres    false            	           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3336                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            
           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            ?            1259    16641    cuenta    TABLE     ?   CREATE TABLE public.cuenta (
    id integer NOT NULL,
    estado boolean,
    numero_cuenta character varying(255),
    saldo double precision,
    tipo character varying(255),
    id_usuario integer
);
    DROP TABLE public.cuenta;
       public         heap    postgres    false            ?            1259    16640    cuenta_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.cuenta_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.cuenta_id_seq;
       public          postgres    false    211                       0    0    cuenta_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.cuenta_id_seq OWNED BY public.cuenta.id;
          public          postgres    false    210            ?            1259    16650    movimientos    TABLE     ?   CREATE TABLE public.movimientos (
    id integer NOT NULL,
    fecha character varying(255),
    saldo double precision,
    tipo_movieminto character varying(255),
    valor integer,
    id_cuenta integer
);
    DROP TABLE public.movimientos;
       public         heap    postgres    false            ?            1259    16649    movimientos_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.movimientos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.movimientos_id_seq;
       public          postgres    false    213                       0    0    movimientos_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.movimientos_id_seq OWNED BY public.movimientos.id;
          public          postgres    false    212            ?            1259    16659    usuarios    TABLE     z  CREATE TABLE public.usuarios (
    id integer NOT NULL,
    apellido character varying(255),
    "contraseña" character varying(255),
    direccion character varying(255),
    edad character varying(255),
    estado boolean,
    genero character varying(255),
    identificacion character varying(255),
    nombre character varying(255),
    telefono character varying(255)
);
    DROP TABLE public.usuarios;
       public         heap    postgres    false            ?            1259    16658    usuarios_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.usuarios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.usuarios_id_seq;
       public          postgres    false    215                       0    0    usuarios_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;
          public          postgres    false    214            g           2604    16644 	   cuenta id    DEFAULT     f   ALTER TABLE ONLY public.cuenta ALTER COLUMN id SET DEFAULT nextval('public.cuenta_id_seq'::regclass);
 8   ALTER TABLE public.cuenta ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    210    211            h           2604    16653    movimientos id    DEFAULT     p   ALTER TABLE ONLY public.movimientos ALTER COLUMN id SET DEFAULT nextval('public.movimientos_id_seq'::regclass);
 =   ALTER TABLE public.movimientos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    213    213            i           2604    16662    usuarios id    DEFAULT     j   ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);
 :   ALTER TABLE public.usuarios ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            ?          0    16641    cuenta 
   TABLE DATA                 public          postgres    false    211   n                  0    16650    movimientos 
   TABLE DATA                 public          postgres    false    213                     0    16659    usuarios 
   TABLE DATA                 public          postgres    false    215   ?                   0    0    cuenta_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.cuenta_id_seq', 17, true);
          public          postgres    false    210                       0    0    movimientos_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.movimientos_id_seq', 68, true);
          public          postgres    false    212                       0    0    usuarios_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.usuarios_id_seq', 13, true);
          public          postgres    false    214            k           2606    16648    cuenta cuenta_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT cuenta_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT cuenta_pkey;
       public            postgres    false    211            m           2606    16657    movimientos movimientos_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.movimientos
    ADD CONSTRAINT movimientos_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.movimientos DROP CONSTRAINT movimientos_pkey;
       public            postgres    false    213            o           2606    16666    usuarios usuarios_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.usuarios DROP CONSTRAINT usuarios_pkey;
       public            postgres    false    215            p           2606    16667    cuenta CuentaUsuario    FK CONSTRAINT     ?   ALTER TABLE ONLY public.cuenta
    ADD CONSTRAINT "CuentaUsuario" FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id) NOT VALID;
 @   ALTER TABLE ONLY public.cuenta DROP CONSTRAINT "CuentaUsuario";
       public          postgres    false    3183    211    215            q           2606    16672    movimientos MovimientosCuenta    FK CONSTRAINT     ?   ALTER TABLE ONLY public.movimientos
    ADD CONSTRAINT "MovimientosCuenta" FOREIGN KEY (id_cuenta) REFERENCES public.cuenta(id) NOT VALID;
 I   ALTER TABLE ONLY public.movimientos DROP CONSTRAINT "MovimientosCuenta";
       public          postgres    false    213    3179    211            ?   ?   x???v
Q???W((M??L?K.M?+ITs?	uV?04?Q()*M?QP7JIJ1736W?Q070 
8?eU?E?4??<	?f
7???????? ???d?cи?b ߘ8???f?%????\?j??2G??d??fl򑱥???qq ??U?          h   x???v
Q???W((M??L???/???L?+?/Vs?	uV?03?QP7202?5??54V?Q0 
??:y??9???:
????\?D?g?n????? ?`t3?? ?*?         ?   x????O? ???+???1J?ƓK?i3?????:
e??zp????}??~??nW??I????#??c?????????aw?n?hR?E???ơyZGU?/?h??X?󢐌cѺ?P?????+Fo?fA<ǾΎ`.????>C????:+?+_Љ?k????Z
!?????k&?I?6Z?H?S?0h0??W?m;?K??????5??{(??????G???!DȲ/?ِ?     