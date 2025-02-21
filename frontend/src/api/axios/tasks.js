import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import axios from "axios";

// Hook for fetching the list of tasks
export const useTasksList = () => {
    return useQuery({
        queryKey: ["tasks"],
        queryFn: async () => {
            const response = await axios.get("http://localhost:7552/task-service/api/tasks");
            return response.data;
        },
    });
};

// Hook for adding a new task
export const useAddTask = () => {
    const queryClient = useQueryClient();

    return useMutation({
        mutationFn: (data) => {
            // Debug log to check if the headers are being passed correctly
            console.log("Posting data with headers:", data);

            return axios.post(
                "http://localhost:7552/task-service/api/tasks",
                data,
                {
                    headers: {
                        "Content-Type": "application/json",  // Assurez-vous que l'en-tête est correctement configuré
                    }
                }
            );
        },
        onSuccess: () => {
            queryClient.invalidateQueries(["tasks"]);
        },
    });
};