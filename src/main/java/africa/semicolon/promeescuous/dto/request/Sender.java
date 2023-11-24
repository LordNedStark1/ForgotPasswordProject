package africa.semicolon.promeescuous.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Sender {
    private String name;
    @NonNull
    private String email;
}
