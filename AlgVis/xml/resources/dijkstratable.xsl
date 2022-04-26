<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="yes" />
<xsl:template match="/">
\documentclass[12pt]{article}
\usepackage[utf8]{inputenc}
\usepackage{booktabs}

\title{Tables}
\author{Akash Bhattacharya}
\date{April 2022}

\begin{document}
\begin{large}
Iterations-\\
\\
\end{large}
\begin{tabular}{c|c|c|c}

\toprule
no. <xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text>Visited Vertex <xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text> Queue <xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text> Distances \\

\midrule
<xsl:for-each select="dijkstra/iterations/iteration">
<xsl:value-of select="iteration_number"> </xsl:value-of><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text><xsl:value-of select="visited_vertex/id"> </xsl:value-of> <xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text> <xsl:for-each select="queue/vertex"> <xsl:sort select="id" order="ascending"></xsl:sort> <xsl:value-of select="id"></xsl:value-of>, </xsl:for-each><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text> <xsl:for-each select="distances/distance"><xsl:sort select="vertex" order="ascending"></xsl:sort> <xsl:value-of select="vertex"> </xsl:value-of> - <xsl:choose><xsl:when test="value=123456.0">\infty</xsl:when><xsl:otherwise><xsl:value-of select="value"></xsl:value-of></xsl:otherwise></xsl:choose> , </xsl:for-each> \\

\midrule
</xsl:for-each>

\bottomrule
\end{tabular}


\end{document}

</xsl:template>

</xsl:stylesheet>