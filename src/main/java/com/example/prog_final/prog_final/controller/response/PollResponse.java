package com.example.prog_final.prog_final.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PollResponse {
    private int id;
    private String title;
    private String creationDatetime;
    private Long idOwner;
    private String owner;
}
