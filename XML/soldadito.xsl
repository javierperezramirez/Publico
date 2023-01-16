<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head></head>
			<body>
				<h1>Información de la canción</h1>
				<h2>espere por favor...</h2>
				<h3>esperando....</h3>
				<br></br>
				<br></br>
				<tr>
					<td><xsl:value-of select="cancion/titulo"/></td>
					<td><xsl:value-of select="cancion/autor"/></td>
				</tr>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>