package net.class101.server1.resolver;

import net.class101.server1.dto.PackageItemsDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class PackageItemListViewResolverTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private final PackageItemListViewResolver sut = new PackageItemListViewResolver();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(null);
    }

    @Test
    public void sut_is_implemented_ViewResolver() {
        assertThat(sut).isInstanceOf(ViewResolver.class);
    }

    @ParameterizedTest
    @ArgumentsSource(PackageItemsDtoAndPrintStringArguments.class)
    public void show_will_return_print_correctly_if_given_PackageItemsDto(
            PackageItemsDto dto, String expected) {
        sut.show(dto);
        assertThat(outputStream.toString()).isEqualTo(expected);
    }

}