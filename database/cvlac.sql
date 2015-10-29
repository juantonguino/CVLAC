-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-10-2015 a las 02:09:05
-- Versión del servidor: 5.6.26
-- Versión de PHP: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cvlac`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE IF NOT EXISTS `articulo` (
  `id_articulo` int(11) NOT NULL COMMENT 'el identificador del articulo',
  `anio_publicacion` year(4) NOT NULL COMMENT 'el año en que se publico el articulo',
  `nombre_articulo` varchar(200) NOT NULL COMMENT 'el nombre del articulo',
  `revista_nombre` varchar(45) NOT NULL COMMENT 'el nombre de la revista donde se publico el articulo',
  `trabajo_investigacion_id` int(11) NOT NULL COMMENT 'eltrabajo de investigacion con el que sirvio de base para el articulo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla encargada del almacenar los articulos  publicados';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo_autor`
--

CREATE TABLE IF NOT EXISTS `articulo_autor` (
  `id` int(11) NOT NULL COMMENT 'el id de la tabla',
  `articulo_id_articulo` int(11) NOT NULL COMMENT 'el identificador del articulo',
  `investigador_identificacion` int(11) NOT NULL COMMENT 'el identificador del investigador'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla de relaciones';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editorial`
--

CREATE TABLE IF NOT EXISTS `editorial` (
  `id` int(11) NOT NULL COMMENT 'el identifidor de la editorial',
  `nombre` varchar(45) NOT NULL COMMENT 'el nombre de la editorial',
  `ciudad` varchar(45) NOT NULL COMMENT 'la cuidad donde se ubica la editorial',
  `pais` varchar(45) NOT NULL COMMENT 'el pais donde se ubica la editorial'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla que almecena la informaciion de la editorial';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE IF NOT EXISTS `evento` (
  `id` int(11) NOT NULL COMMENT 'el identificador del evento',
  `lugar` varchar(45) NOT NULL COMMENT 'el lugar donde se realizo el evento',
  `fecha_inicio` date NOT NULL COMMENT 'fecha en la que se inicio el evento',
  `fecha_fin` date NOT NULL COMMENT 'la fecha en la que se finalizo el evento',
  `tipo` varchar(45) NOT NULL COMMENT 'el tipo del evento, este puede ser congreso seminario foro psinopcio , otro',
  `caracter` varchar(45) NOT NULL COMMENT 'el caracter del evento si es nacional o internacional'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla que almacena la informacion de los eventos';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gurpo_investigacion`
--

CREATE TABLE IF NOT EXISTS `gurpo_investigacion` (
  `nombre` varchar(45) NOT NULL COMMENT 'el nombre del grupo de investigacion.',
  `linea_investigacion` varchar(45) NOT NULL COMMENT 'la linea de investigacion que le corresponde al grupo de invsetigacion',
  `categorizacion_grupo` varchar(45) NOT NULL COMMENT 'la clasificacion de categorizacion del grupo esta puede ser reconocido, no reconocido d, c, b, a,a1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla encargada de almacenar los datos de los grupos de investigacion';

--
-- Volcado de datos para la tabla `gurpo_investigacion`
--

INSERT INTO `gurpo_investigacion` (`nombre`, `linea_investigacion`, `categorizacion_grupo`) VALUES
('gismar', 'tecnologia', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `investigador`
--

CREATE TABLE IF NOT EXISTS `investigador` (
  `identificacion` int(11) NOT NULL COMMENT 'la identificacion del investigador',
  `nombres` varchar(100) NOT NULL COMMENT 'los nombres del investigador',
  `apellidos` varchar(100) NOT NULL COMMENT 'los apellidos del investigador',
  `activo` tinyint(1) NOT NULL COMMENT 'si el investigador esta activo o no',
  `gurpos_investigacion_nombre` varchar(45) NOT NULL COMMENT 'el grupo al que pertenece el investigador'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla que almacena los datos del administrador\n';

--
-- Volcado de datos para la tabla `investigador`
--

INSERT INTO `investigador` (`identificacion`, `nombres`, `apellidos`, `activo`, `gurpos_investigacion_nombre`) VALUES
(1085309822, 'juan', 'tonguino', 1, 'gismar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `investigador_trabajo_investigacion`
--

CREATE TABLE IF NOT EXISTS `investigador_trabajo_investigacion` (
  `id` int(11) NOT NULL,
  `investigador_identificacion` int(11) NOT NULL,
  `trabajo_investigacion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE IF NOT EXISTS `libro` (
  `id` int(11) NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `anio_publicacin` year(4) NOT NULL,
  `editorial_id` int(11) NOT NULL,
  `trabajo_investigacion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro_autores`
--

CREATE TABLE IF NOT EXISTS `libro_autores` (
  `id` int(11) NOT NULL,
  `investigador_identificacion` int(11) NOT NULL,
  `libro_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nivel_formacion`
--

CREATE TABLE IF NOT EXISTS `nivel_formacion` (
  `id_estudios_realizados` int(11) NOT NULL COMMENT 'el didentificador de la tabla',
  `titulo` varchar(45) NOT NULL COMMENT 'el titulo del nivel de formacion',
  `nombre_trabajo_grado` varchar(200) NOT NULL COMMENT 'el nombre del trabajo de grado',
  `anio_inicio` year(4) NOT NULL COMMENT 'el año en el que se empezo con la formacion academica',
  `anio_fin` year(4) DEFAULT NULL COMMENT 'en año en el que se finalizo la formacion academica',
  `investigador_identificacion` int(11) NOT NULL COMMENT 'la identificacion del investigador '
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tabla encargada de almacenar el nivel de formacion de los investigadores\n';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ponencia`
--

CREATE TABLE IF NOT EXISTS `ponencia` (
  `id` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `anio` year(4) NOT NULL,
  `evento_id` int(11) NOT NULL,
  `trabajo_investigacion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ponencia_autor`
--

CREATE TABLE IF NOT EXISTS `ponencia_autor` (
  `id` int(11) NOT NULL,
  `ponencia_id` int(11) NOT NULL,
  `investigador_identificacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `revista`
--

CREATE TABLE IF NOT EXISTS `revista` (
  `nombre` varchar(45) NOT NULL,
  `indexada` tinyint(1) NOT NULL,
  `categorizacion` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajo_investigacion`
--

CREATE TABLE IF NOT EXISTS `trabajo_investigacion` (
  `id` int(11) NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `fecha_inicio` year(4) NOT NULL,
  `fecha_fin` year(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `nombre` varchar(100) NOT NULL,
  `contrasenia` varchar(200) NOT NULL,
  `investigador_identificacion` int(11) DEFAULT NULL,
  `esadministrador` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nombre`, `contrasenia`, `investigador_identificacion`, `esadministrador`) VALUES
('diego', '1234', 1085309822, 0),
('juan', '123', 1085309822, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`id_articulo`),
  ADD KEY `fk_articulo_revista1_idx` (`revista_nombre`),
  ADD KEY `fk_articulo_trabajo_investigacion1_idx` (`trabajo_investigacion_id`);

--
-- Indices de la tabla `articulo_autor`
--
ALTER TABLE `articulo_autor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_articulo_autor_articulo1_idx` (`articulo_id_articulo`),
  ADD KEY `fk_articulo_autor_investigador1_idx` (`investigador_identificacion`);

--
-- Indices de la tabla `editorial`
--
ALTER TABLE `editorial`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `gurpo_investigacion`
--
ALTER TABLE `gurpo_investigacion`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `investigador`
--
ALTER TABLE `investigador`
  ADD PRIMARY KEY (`identificacion`),
  ADD KEY `fk_investigador_gurpos_investigacion_idx` (`gurpos_investigacion_nombre`);

--
-- Indices de la tabla `investigador_trabajo_investigacion`
--
ALTER TABLE `investigador_trabajo_investigacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_investigador_trabajo_investigacion_investigador1_idx` (`investigador_identificacion`),
  ADD KEY `fk_investigador_trabajo_investigacion_trabajo_investigacion_idx` (`trabajo_investigacion_id`);

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_libro_editorial1_idx` (`editorial_id`),
  ADD KEY `fk_libro_trabajo_investigacion1_idx` (`trabajo_investigacion_id`);

--
-- Indices de la tabla `libro_autores`
--
ALTER TABLE `libro_autores`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_libro_autores_investigador1_idx` (`investigador_identificacion`),
  ADD KEY `fk_libro_autores_libro1_idx` (`libro_id`);

--
-- Indices de la tabla `nivel_formacion`
--
ALTER TABLE `nivel_formacion`
  ADD PRIMARY KEY (`id_estudios_realizados`),
  ADD KEY `fk_estudios_realizados_investigador1_idx` (`investigador_identificacion`);

--
-- Indices de la tabla `ponencia`
--
ALTER TABLE `ponencia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ponencia_evento1_idx` (`evento_id`),
  ADD KEY `fk_ponencia_trabajo_investigacion1_idx` (`trabajo_investigacion_id`);

--
-- Indices de la tabla `ponencia_autor`
--
ALTER TABLE `ponencia_autor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ponencia_autor_ponencia1_idx` (`ponencia_id`),
  ADD KEY `fk_ponencia_autor_investigador1_idx` (`investigador_identificacion`);

--
-- Indices de la tabla `revista`
--
ALTER TABLE `revista`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `trabajo_investigacion`
--
ALTER TABLE `trabajo_investigacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `fk_usuario_investigador1_idx` (`investigador_identificacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `id_articulo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'el identificador del articulo';
--
-- AUTO_INCREMENT de la tabla `articulo_autor`
--
ALTER TABLE `articulo_autor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'el id de la tabla';
--
-- AUTO_INCREMENT de la tabla `editorial`
--
ALTER TABLE `editorial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'el identifidor de la editorial';
--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'el identificador del evento';
--
-- AUTO_INCREMENT de la tabla `investigador_trabajo_investigacion`
--
ALTER TABLE `investigador_trabajo_investigacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `libro_autores`
--
ALTER TABLE `libro_autores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `nivel_formacion`
--
ALTER TABLE `nivel_formacion`
  MODIFY `id_estudios_realizados` int(11) NOT NULL AUTO_INCREMENT COMMENT 'el didentificador de la tabla';
--
-- AUTO_INCREMENT de la tabla `ponencia`
--
ALTER TABLE `ponencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `ponencia_autor`
--
ALTER TABLE `ponencia_autor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `trabajo_investigacion`
--
ALTER TABLE `trabajo_investigacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD CONSTRAINT `fk_articulo_revista1` FOREIGN KEY (`revista_nombre`) REFERENCES `revista` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_articulo_trabajo_investigacion1` FOREIGN KEY (`trabajo_investigacion_id`) REFERENCES `trabajo_investigacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `articulo_autor`
--
ALTER TABLE `articulo_autor`
  ADD CONSTRAINT `fk_articulo_autor_articulo1` FOREIGN KEY (`articulo_id_articulo`) REFERENCES `articulo` (`id_articulo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_articulo_autor_investigador1` FOREIGN KEY (`investigador_identificacion`) REFERENCES `investigador` (`identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `investigador`
--
ALTER TABLE `investigador`
  ADD CONSTRAINT `fk_investigador_gurpos_investigacion` FOREIGN KEY (`gurpos_investigacion_nombre`) REFERENCES `gurpo_investigacion` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `investigador_trabajo_investigacion`
--
ALTER TABLE `investigador_trabajo_investigacion`
  ADD CONSTRAINT `fk_investigador_trabajo_investigacion_investigador1` FOREIGN KEY (`investigador_identificacion`) REFERENCES `investigador` (`identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_investigador_trabajo_investigacion_trabajo_investigacion1` FOREIGN KEY (`trabajo_investigacion_id`) REFERENCES `trabajo_investigacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `fk_libro_editorial1` FOREIGN KEY (`editorial_id`) REFERENCES `editorial` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_libro_trabajo_investigacion1` FOREIGN KEY (`trabajo_investigacion_id`) REFERENCES `trabajo_investigacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `libro_autores`
--
ALTER TABLE `libro_autores`
  ADD CONSTRAINT `fk_libro_autores_investigador1` FOREIGN KEY (`investigador_identificacion`) REFERENCES `investigador` (`identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_libro_autores_libro1` FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `nivel_formacion`
--
ALTER TABLE `nivel_formacion`
  ADD CONSTRAINT `fk_estudios_realizados_investigador1` FOREIGN KEY (`investigador_identificacion`) REFERENCES `investigador` (`identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ponencia`
--
ALTER TABLE `ponencia`
  ADD CONSTRAINT `fk_ponencia_evento1` FOREIGN KEY (`evento_id`) REFERENCES `evento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ponencia_trabajo_investigacion1` FOREIGN KEY (`trabajo_investigacion_id`) REFERENCES `trabajo_investigacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ponencia_autor`
--
ALTER TABLE `ponencia_autor`
  ADD CONSTRAINT `fk_ponencia_autor_investigador1` FOREIGN KEY (`investigador_identificacion`) REFERENCES `investigador` (`identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ponencia_autor_ponencia1` FOREIGN KEY (`ponencia_id`) REFERENCES `ponencia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_usuario_investigador1` FOREIGN KEY (`investigador_identificacion`) REFERENCES `investigador` (`identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
