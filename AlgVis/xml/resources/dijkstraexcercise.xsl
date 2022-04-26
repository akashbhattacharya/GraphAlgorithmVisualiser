<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="yes" />
<xsl:template match="/">
\documentclass[12pt]{article}
\usepackage[utf8]{inputenc}
\usepackage{tikz}
\usepackage{booktabs}

\title{Dijkstra}
\author{Akash Bhattacharya}
\date{April 2022}

\begin{document}
\begin{large}
Dijkstra-\\
\\
\end{large}

\begin{tikzpicture}[x=6pt,y=7.5pt,font={\footnotesize\strut},every node/.style={circle,draw,fill=black!15}]
<xsl:for-each select="Dijkstra/graph/vertices/vertex">
\node (<xsl:value-of select="id"></xsl:value-of>) at (<xsl:value-of select="x_coordinate*7.5"></xsl:value-of>,<xsl:value-of select="-7.5*y_coordinate"></xsl:value-of>) {<xsl:value-of select="id"></xsl:value-of>};
</xsl:for-each>
\path[every node/.style={draw=white,fill=white,font={\footnotesize}}]
<xsl:for-each select="Dijkstra/graph/edges/edge">
(<xsl:value-of select="endpoint1"></xsl:value-of>) edge node {<xsl:value-of select="weight"></xsl:value-of>} (<xsl:value-of select="endpoint2"></xsl:value-of>)
</xsl:for-each>
;
\end{tikzpicture}\\[3\baselineskip]
\Large
\begin{tabular}{<xsl:for-each select="Dijkstra/graph/vertices/vertex">c|</xsl:for-each>c}
\toprule
Queue Elements<xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text><xsl:for-each select="Dijkstra/graph/vertices/vertex"><xsl:sort select="id" order="ascending"></xsl:sort><xsl:value-of select="id"></xsl:value-of><xsl:if test="position()!=last()" ><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text></xsl:if></xsl:for-each> \\
\midrule
-<xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text>0.0<xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text><xsl:for-each select="Dijkstra/iterations/iteration"><xsl:if test="position()!=last()" >\infty<xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text></xsl:if></xsl:for-each>
\midrule
<xsl:for-each select="Dijkstra/iterations/iteration"><xsl:value-of select="iteration_number"></xsl:value-of><xsl:for-each select="../iteration"><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text></xsl:for-each> \\
\midrule
</xsl:for-each>
\bottomrule
\end{tabular}
\newpage
Solution-\\
\\
\begin{tabular}{<xsl:for-each select="Dijkstra/graph/vertices/vertex">c|</xsl:for-each>c}
\toprule
Queue Elements<xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text><xsl:for-each select="Dijkstra/iterations/iteration"><xsl:sort select="vertex/id" order="ascending"></xsl:sort><xsl:value-of select="visited_vertex/id"></xsl:value-of><xsl:if test="position()!=last()" ><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text></xsl:if></xsl:for-each> \\
\midrule
-<xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text>0.0<xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text><xsl:for-each select="Dijkstra/iterations/iteration"><xsl:if test="position()!=last()" >\infty<xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text></xsl:if></xsl:for-each>
\midrule
<xsl:for-each select="Dijkstra/iterations/iteration"><xsl:value-of select="iteration_number"></xsl:value-of><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text><xsl:for-each select="distances/distance"><xsl:sort select="vertex" order="ascending"></xsl:sort><xsl:choose><xsl:when test="value=123456.0">\infty</xsl:when><xsl:otherwise><xsl:value-of select="value"></xsl:value-of></xsl:otherwise></xsl:choose><xsl:if test="position()!=last()" ><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text></xsl:if></xsl:for-each> \\
\midrule
</xsl:for-each>
\bottomrule
\end{tabular}

\end{document}



</xsl:template>

</xsl:stylesheet>