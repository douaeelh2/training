"use client";

import {
    Box, Card, CardContent, Typography, Button, TextField,
    Dialog, DialogTitle, DialogContent, DialogActions
} from "@mui/material";
import Link from "next/link";
import { useState } from "react";
import { useForm, Controller } from "react-hook-form";
import { useTasksList, useAddTask } from "@/api/axios/tasks";
import Navbar from "@/app/widgets/navbar";

export default function TaskList() {
    const { data: fetchedTasks = [], isLoading, error } = useTasksList();
    const { mutate: addTask, isLoading: isAdding } = useAddTask();

    const [openDialog, setOpenDialog] = useState(false);

    // Utilisation de React Hook Form
    const { control, handleSubmit, setValue, formState: { errors } } = useForm({
        defaultValues: {
            name: "",
            description: "",
            status: "",
            technologies: "",
            employeeId: "",
        }
    });

    const handleOpenDialog = () => setOpenDialog(true);
    const handleCloseDialog = () => setOpenDialog(false);

    const onSubmit = (data) => {
        const taskData = {
            ...data,
            technologies: data.technologies.split(",").map((tech, index) => ({
                id: index + 1,
                name: tech.trim(),
            })),
        };

        addTask(taskData, {
            onSuccess: () => {
                handleCloseDialog();
            },
        });
    };

    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error fetching tasks: {error.message}</div>;

    return (
        <div>
            <Navbar />
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', my: 5, px: 3 }}>
                <Typography variant="h4" component="h2">Task List</Typography>
                <Box>
                    <Button variant="contained" color="primary" onClick={handleOpenDialog} sx={{ marginTop: 2 }}>
                        Add Task
                    </Button>
                </Box>
            </Box>

            {/* Task List */}
            <Box
                sx={{
                    display: "grid",
                    gridTemplateColumns: "repeat(auto-fill, minmax(300px, 1fr))",
                    gap: 2,
                    padding: 5,
                    textAlign: "center",
                }}
            >
                {fetchedTasks.map((task) => (
                    <Card key={task.id} sx={{ padding: 2, textAlign: "left" }}>
                        <CardContent>
                            <Typography variant="h5" component="div" sx={{ marginBottom: 2 }}>{task.name}</Typography>
                            <Typography variant="body1" color="text.secondary" sx={{ marginBottom: 2 }}>
                                {task.description}
                            </Typography>
                            <Typography variant="body2" color="text.secondary" sx={{ marginBottom: 2 }}>
                                <strong>Status:</strong> {task.status}
                            </Typography>
                            <Typography variant="body2" color="text.secondary" sx={{ marginBottom: 2 }}>
                                <strong>Technologies:</strong> {task.technologies.map(tech => tech.name).join(', ')}
                            </Typography>
                            <Typography variant="body2" color="text.secondary" sx={{ marginBottom: 2 }}>
                                <strong>Assigned Employee ID:</strong> {task.employeeId}
                            </Typography>
                            <Link href={`/tasks/${task.id}`}>
                                <Button variant="contained" color="primary">View Task</Button>
                            </Link>
                        </CardContent>
                    </Card>
                ))}

                <Dialog open={openDialog} onClose={handleCloseDialog}>
                    <DialogTitle>Add a New Task</DialogTitle>
                    <DialogContent>
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <Controller
                                name="name"
                                control={control}
                                rules={{ required: "Name is required" }}
                                render={({ field }) => (
                                    <TextField
                                        {...field}
                                        fullWidth
                                        label="Name"
                                        variant="outlined"
                                        sx={{ marginBottom: 2 }}
                                        error={!!errors.name}
                                        helperText={errors.name ? errors.name.message : ''}
                                    />
                                )}
                            />
                            <Controller
                                name="description"
                                control={control}
                                rules={{ required: "Description is required" }}
                                render={({ field }) => (
                                    <TextField
                                        {...field}
                                        fullWidth
                                        label="Description"
                                        variant="outlined"
                                        multiline
                                        rows={4}
                                        sx={{ marginBottom: 2 }}
                                        error={!!errors.description}
                                        helperText={errors.description ? errors.description.message : ''}
                                    />
                                )}
                            />
                            <Controller
                                name="status"
                                control={control}
                                rules={{ required: "Status is required" }}
                                render={({ field }) => (
                                    <TextField
                                        {...field}
                                        fullWidth
                                        label="Status"
                                        variant="outlined"
                                        sx={{ marginBottom: 2 }}
                                        error={!!errors.status}
                                        helperText={errors.status ? errors.status.message : ''}
                                    />
                                )}
                            />
                            <Controller
                                name="technologies"
                                control={control}
                                rules={{ required: "Technologies are required" }}
                                render={({ field }) => (
                                    <TextField
                                        {...field}
                                        fullWidth
                                        label="Technologies (comma separated)"
                                        variant="outlined"
                                        sx={{ marginBottom: 2 }}
                                        error={!!errors.technologies}
                                        helperText={errors.technologies ? errors.technologies.message : ''}
                                    />
                                )}
                            />
                            <Controller
                                name="employeeId"
                                control={control}
                                rules={{ required: "Employee ID is required" }}
                                render={({ field }) => (
                                    <TextField
                                        {...field}
                                        fullWidth
                                        label="Employee ID"
                                        variant="outlined"
                                        sx={{ marginBottom: 2 }}
                                        error={!!errors.employeeId}
                                        helperText={errors.employeeId ? errors.employeeId.message : ''}
                                    />
                                )}
                            />
                            <DialogActions sx={{ marginBottom: 2 }}>
                                <Button type="submit" color="primary" variant="contained" disabled={isAdding}>
                                    {isAdding ? "Adding..." : "Add Task"}
                                </Button>
                                <Button onClick={handleCloseDialog} color="secondary">
                                    Cancel
                                </Button>
                            </DialogActions>
                        </form>
                    </DialogContent>
                </Dialog>
            </Box>
        </div>
    );
}
