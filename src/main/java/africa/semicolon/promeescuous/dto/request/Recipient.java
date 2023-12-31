package africa.semicolon.promeescuous.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Recipient {
    private String name;
    @NonNull
    private String email;
}
