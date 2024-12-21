import React from "react";
import Container from "@mui/material/Container"; // Material UI'den Container bileşenini içe aktardık kullanabilmek için

function PageContainer({ children }) {
    return (
        <Container maxWidth="lg">
            {children}
        </Container>
    );
}

export default PageContainer;
