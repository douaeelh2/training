import {AppBar,Button, Toolbar, Typography} from "@mui/material";


export default function Navbar() {

    return (
        <AppBar position="sticky">
            <Toolbar>
                <Typography variant="h6" sx={{ flexGrow: 1 }}>
                    TaskManager
                </Typography>
                <Button color="inherit" onClick={() => router.push("/pages/tasks")}>
                    Tasks
                </Button>
                <Button color="inherit" onClick={() => router.push("/pages/employees")}>
                    Employees
                </Button>
            </Toolbar>
        </AppBar>
    );
}


