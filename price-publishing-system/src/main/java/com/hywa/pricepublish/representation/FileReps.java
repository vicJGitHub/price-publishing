package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileReps {

    private Long total;

    private List<File> file;
}
