<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" omit-xml-declaration="yes" />
<xsl:template match="/">
\documentclass[12pt]{article}
\usepackage[utf8]{inputenc}
\usepackage{tikz}
\usepackage{booktabs}

\title{BFSSlides}
\author{Akash Bhattacharya}
\date{April 2022}

\begin{document}
\begin{large}
Breath First Search-\\
\\
\end{large}

\begin{tikzpicture}[x=7pt,y=10pt,font={\footnotesize\strut},every node/.style={circle,draw,fill=black!15}]
<xsl:for-each select="BreathFirstSearch/VisitedVertex/vertex">
\node (<xsl:value-of select="id"></xsl:value-of>) at (<xsl:value-of select="x_coordinate*5"></xsl:value-of>,<xsl:value-of select="-5*y_coordinate"></xsl:value-of>) {<xsl:value-of select="id"></xsl:value-of>};
</xsl:for-each>
\path[every node/.style={draw=white,fill=white,font={\footnotesize}}]
<xsl:for-each select="BreathFirstSearch/Edges/edge">
(<xsl:value-of select="endpoint1"></xsl:value-of>) edge node {} (<xsl:value-of select="endpoint2"></xsl:value-of>)
</xsl:for-each>
;
\end{tikzpicture}
\pagebreak

<xsl:for-each select="BreathFirstSearch/VisitedVertex/vertex">
\begin{large}
Breath First Search-\\
\\
\end{large}
\begin{tikzpicture}[x=7pt,y=10pt,font={\footnotesize\strut},every node/.style={circle,draw,fill=black!15}]
<xsl:for-each select="../vertex">
\node (<xsl:value-of select="id"></xsl:value-of>) at (<xsl:value-of select="x_coordinate*5"></xsl:value-of>,<xsl:value-of select="-5*y_coordinate"></xsl:value-of>) {<xsl:value-of select="id"></xsl:value-of>};
</xsl:for-each>
\node[fill = green](<xsl:value-of select="id"></xsl:value-of>) at (<xsl:value-of select="x_coordinate*5"></xsl:value-of>,<xsl:value-of select="-5*y_coordinate"></xsl:value-of>) {<xsl:value-of select="id"></xsl:value-of>};

\path[every node/.style={draw=white,fill=white,font={\footnotesize}}]
<xsl:for-each select="../../Edges/edge">
(<xsl:value-of select="endpoint1"></xsl:value-of>) edge node {} (<xsl:value-of select="endpoint2"></xsl:value-of>)
</xsl:for-each>
;
\end{tikzpicture}\\[5\baselineskip]
\pagebreak
</xsl:for-each>


\end{document}



</xsl:template>

</xsl:stylesheet>