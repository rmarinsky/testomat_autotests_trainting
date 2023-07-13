package io.testomat.ui.dtos;

import io.testomat.ui.pages.ProjectsPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseProjectInfo {

    private String name;
    private String count;

    private ProjectType label;

    @AllArgsConstructor
    public enum ProjectType {
        CLASSICAL("Classical"),
        BDD("BDD");

        public final String label;

        @Override
        public String toString() {
            return label;
        }
    }

}
