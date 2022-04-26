<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="yes" />
<xsl:template match="/">
\documentclass[12pt]{article}
\usepackage[utf8]{inputenc}
\usepackage{tikz}
\usepackage{booktabs}
\usepackage{amsmath}

\title{FloydWarshall}
\author{Akash Bhattacharya}
\date{April 2022}

\begin{document}
\begin{Large}
Floyd Warshall-\\
\\
\end{Large}

\begin{tikzpicture}[x=6pt,y=7.5pt,font={\footnotesize\strut},every node/.style={circle,draw,fill=black!15}]
<xsl:for-each select="FloydWarshall/graph/vertices/vertex">
\node (<xsl:value-of select="id"></xsl:value-of>) at (<xsl:value-of select="x_coordinate*7.5"></xsl:value-of>,<xsl:value-of select="-7.5*y_coordinate"></xsl:value-of>) {<xsl:value-of select="id"></xsl:value-of>};
</xsl:for-each>
\path[every node/.style={draw=white,fill=white,font={\footnotesize}}]
<xsl:for-each select="FloydWarshall/graph/edges/edge">
(<xsl:value-of select="endpoint1"></xsl:value-of>) edge node {<xsl:value-of select="weight"></xsl:value-of>} (<xsl:value-of select="endpoint2"></xsl:value-of>)
</xsl:for-each>
;
\end{tikzpicture}\\[1\baselineskip]
\large

\begin{tabular}{<xsl:for-each select="FloydWarshall/graph/vertices/vertex">c|</xsl:for-each>c}
\toprule
Vertices<xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text><xsl:for-each select="FloydWarshall/table/vertices/vertex"><xsl:value-of select ="."></xsl:value-of> <xsl:if test="position()!=last()" ><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text></xsl:if></xsl:for-each>\\
\midrule
<xsl:for-each select="FloydWarshall/table/row"><xsl:value-of select="row_vertex"></xsl:value-of><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text><xsl:for-each select="column"><xsl:choose><xsl:when test=".=12345.6">\infty</xsl:when><xsl:otherwise><xsl:value-of select="."></xsl:value-of></xsl:otherwise></xsl:choose><xsl:if test="position()!=last()" ><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text></xsl:if></xsl:for-each>\\
\midrule
</xsl:for-each>
\bottomrule
\end{tabular}
\pagebreak\\
\begin{Large}
Iterations-\\
\\
\end{Large}
\LARGE

<xsl:for-each select="FloydWarshall/iterations/iteration">
\[
I<xsl:value-of select="iteration_number"></xsl:value-of>=
\begin{bmatrix}
<xsl:for-each select="row"><xsl:for-each select="column"><xsl:choose><xsl:when test=".=12345.6">\infty</xsl:when><xsl:otherwise><xsl:value-of select="."></xsl:value-of></xsl:otherwise></xsl:choose><xsl:if test="position()!=last()" ><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text></xsl:if></xsl:for-each>\\</xsl:for-each>
\end{bmatrix}
\]
</xsl:for-each>
\end{document}



</xsl:template>

</xsl:stylesheet>