import {
    Avatar,
    Box,
    Flex,
    Icon,
    Text,
    Stack,
    Image,
    Button,
    Heading,
    Drawer,
    DrawerContent,
    IconButton,
    useDisclosure,
    DrawerOverlay,
    useColorModeValue
} from '@chakra-ui/react';
// Here we have used react-icons package for the icons
import { FaBell } from 'react-icons/fa';
import { AiOutlineTeam, AiOutlineHome } from 'react-icons/ai';
import { BsFolder2, BsCalendarCheck } from 'react-icons/bs';
import { FiMenu } from 'react-icons/fi';
import { RiFlashlightFill } from 'react-icons/ri';

export default function Index({children}) {
    const { isOpen, onClose, onOpen } = useDisclosure();

    return (
        <Box as="section" bg={useColorModeValue('gray.50', 'gray.700')} minH="100vh">
            <SidebarContent display={{ base: 'none', md: 'unset' }} />
            <Drawer isOpen={isOpen} onClose={onClose} placement="left">
                <DrawerOverlay />
                <DrawerContent>
                    <SidebarContent w="full" borderRight="none" />
                </DrawerContent>
            </Drawer>
            <Box ml={{ base: 0, md: 60 }} transition=".3s ease">
                <Flex
                    as="header"
                    align="center"
                    justifyContent={{ base: 'space-between', md: 'flex-end' }}
                    w="full"
                    px="4"
                    borderBottomWidth="1px"
                    borderColor={useColorModeValue('inherit', 'gray.700')}
                    bg={useColorModeValue('white', 'gray.800')}
                    boxShadow="sm"
                    h="14"
                >
                    <IconButton
                        aria-label="Menu"
                        display={{ base: 'inline-flex', md: 'none' }}
                        onClick={onOpen}
                        icon={<FiMenu />}
                        size="md"
                    />

                    <Flex align="center">
                        <Icon color="gray.500" as={FaBell} cursor="pointer" />
                        <Avatar
                            ml="4"
                            size="sm"
                            name="Ahmad"
                            src="https://avatars2.githubusercontent.com/u/37842853?v=4"
                            cursor="pointer"
                        />
                    </Flex>
                </Flex>

                <Box as="main" p={14} minH="25rem" bg={useColorModeValue('auto', 'gray.800')}>
                    {children}
                </Box>
            </Box>
        </Box>
    );
}

const SidebarContent = ({ ...props }) => (
    <Box
        as="nav"
        pos="fixed"
        top="0"
        left="0"
        zIndex="sticky"
        h="full"
        pb="10"
        overflowX="hidden"
        overflowY="auto"
        bg={useColorModeValue('white', 'gray.800')}
        borderColor={useColorModeValue('inherit', 'gray.700')}
        borderRightWidth="1px"
        w="60"
        {...props}
    >
        <Flex px="4" py="5" alignItems={"center"} flexDirection={"column"}>
            <Text
                fontSize="2xl"
                color={useColorModeValue('brand.500', 'white')}
                fontWeight="semibold"


            >
                DRAOUA
            </Text>
            <Icon as={RiFlashlightFill} h={8} w={8}  />
        </Flex>
        <Flex direction="column" as="nav" fontSize="md" color="gray.600" aria-label="Main Navigation">
            <NavItem icon={AiOutlineHome}>Dashboard</NavItem>
            <NavItem icon={AiOutlineTeam}>Team</NavItem>
            <NavItem icon={BsFolder2}>Projects</NavItem>
            <NavItem icon={BsCalendarCheck}>Calendar</NavItem>
        </Flex>
    </Box>
);

const NavItem = (props) => {
    const color = useColorModeValue('gray.600', 'gray.300');

    const { icon, children } = props;
    return (
        <Flex
            align="center"
            px="4"
            py="3"
            cursor="pointer"
            role="group"
            fontWeight="semibold"
            transition=".15s ease"
            color={useColorModeValue('inherit', 'gray.400')}
            _hover={{
                bg: useColorModeValue('gray.100', 'gray.900'),
                color: useColorModeValue('gray.900', 'gray.200')
            }}
        >
            {icon && (
                <Icon
                    mx="2"
                    boxSize="4"
                    _groupHover={{
                        color: color
                    }}
                    as={icon}
                />
            )}
            {children}
        </Flex>
    );
};