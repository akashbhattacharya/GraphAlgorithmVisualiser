<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="yes" />
<xsl:template match="/">
\documentclass[10pt]{article}
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

\begin{tikzpicture}[x=7pt,y=10pt,font={\footnotesize\strut},every node/.style={circle,draw,fill=black!15}]
<xsl:for-each select="dijkstra/graph/vertices/vertex">
\node (<xsl:value-of select="id"></xsl:value-of>) at (<xsl:value-of select="x_coordinate*7.5"></xsl:value-of>,<xsl:value-of select="-7.5*y_coordinate"></xsl:value-of>) {<xsl:value-of select="id"></xsl:value-of>};
</xsl:for-each>
\path[every node/.style={draw=white,fill=white,font={\footnotesize}}]
<xsl:for-each select="dijkstra/graph/edges/edge">
(<xsl:value-of select="endpoint1"></xsl:value-of>) edge node {<xsl:value-of select="weight"></xsl:value-of>} (<xsl:value-of select="endpoint2"></xsl:value-of>)
</xsl:for-each>
;
\end{tikzpicture}
\pagebreak

<xsl:for-each select="Dijkstra/iterations/iteration">

\begin{tikzpicture}[x=7pt,y=10pt,font={\footnotesize\strut},every node/.style={circle,draw,fill=blue!50}]
<xsl:for-each select="../../graph/vertices/vertex">

\node (<xsl:value-of select="id"></xsl:value-of>) at (<xsl:value-of select="x_coordinate*7.5"></xsl:value-of>,<xsl:value-of select="-7.5*y_coordinate"></xsl:value-of>) {<xsl:value-of select="id"></xsl:value-of>};
</xsl:for-each>

<xsl:for-each select="queue/vertex">
\node[fill=black!15] (<xsl:value-of select="id"></xsl:value-of>) at((<xsl:value-of select="x_coordinate*7.5"></xsl:value-of>,<xsl:value-of select="-7.5*y_coordinate"></xsl:value-of>) {<xsl:value-of select="id"></xsl:value-of>};
</xsl:for-each>
\node[fill=green] (<xsl:value-of select="visited_vertex/id"></xsl:value-of>) at((<xsl:value-of select="visited_vertex/x_coordinate*7.5"></xsl:value-of>,<xsl:value-of select="-7.5*visited_vertex/y_coordinate"></xsl:value-of>) {<xsl:value-of select="visited_vertex/id"></xsl:value-of>};

\path[every node/.style={draw=white,fill=white,font={\footnotesize}}]
<xsl:for-each select="../../graph/edges/edge">
(<xsl:value-of select="endpoint1"></xsl:value-of>) edge node {<xsl:value-of select="weight"></xsl:value-of>} (<xsl:value-of select="endpoint2"></xsl:value-of>)
</xsl:for-each>

;
\end{tikzpicture}\\[5\baselineskip]
\begin{tabular}{c|c|c|c}
\toprule
no. <xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text>Visited Vertex <xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text> Queue <xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text> Distances \\
\midrule
<xsl:value-of select="iteration_number"> </xsl:value-of><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text><xsl:value-of select="visited_vertex/id"> </xsl:value-of> <xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text> <xsl:for-each select="queue/vertex"> <xsl:sort select="id" order="ascending"></xsl:sort> <xsl:value-of select="id"></xsl:value-of>, </xsl:for-each><xsl:text disable-output-escaping="yes"><![CDATA[&]]></xsl:text> <xsl:for-each select="distances/distance"><xsl:sort select="vertex" order="ascending"></xsl:sort> <xsl:value-of select="vertex"> </xsl:value-of> - <xsl:choose><xsl:when test="value=123456.0">\infty</xsl:when><xsl:otherwise><xsl:value-of select="value"></xsl:value-of></xsl:otherwise></xsl:choose> , </xsl:for-each> \\

\bottomrule
\end{tabular}
\pagebreak
</xsl:for-each>


\end{document}



</xsl:template>

</xsl:stylesheet>