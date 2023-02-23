package ca.bc.gov.backendstartapi.endpoint;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.bc.gov.backendstartapi.enums.DescribedEnum;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

abstract class DescribedEnumEndpointTest<E extends Enum<E> & DescribedEnum> {

  protected Class<E> enumClass;

  protected String endpointPrefix;

  private MockMvc mockMvc;

  private WebApplicationContext webApplicationContext;

  DescribedEnumEndpointTest(WebApplicationContext webApplicationContext) {
    this.webApplicationContext = webApplicationContext;
  }

  @BeforeEach
  void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  void fetchAllCodes() throws Exception {
    mockMvc
        .perform(get(endpointPrefix).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpectAll(this.generateMatchers(enumClass.getEnumConstants()));
  }

  @Test
  void fetchExistentCode() throws Exception {
    var value = enumClass.getEnumConstants()[0];
    mockMvc
        .perform(
            get(String.format("%s/%s", endpointPrefix, value.name()))
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpectAll(
            jsonPath("code", IsEqual.equalTo(value.toString())),
            jsonPath("description", IsEqual.equalTo(value.description())));
  }

  @Test
  void fetchNonExistentCode() throws Exception {
    mockMvc
        .perform(
            get(endpointPrefix + "/NOT_THERE")
                .with(csrf().asHeader())
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string(""));
  }

  /**
   * Generate an array of {@link ResultMatcher ResultMatchers} for the properties of {@link
   * DescribedEnum DescribedEnums}.
   *
   * @param values an array with the described enums to be checked
   * @return an array with matchers for each enum in {@code values}
   * @see ResultActions#andExpectAll(ResultMatcher...)
   */
  private ResultMatcher[] generateMatchers(E[] values) {
    return IntStream.range(0, values.length)
        .boxed()
        .flatMap(
            i ->
                Stream.of(
                    jsonPath(String.format("[%s].code", i), IsEqual.equalTo(values[i].name())),
                    jsonPath(
                        String.format("[%s].description", i),
                        IsEqual.equalTo(values[i].description()))))
        .toArray(ResultMatcher[]::new);
  }
}
